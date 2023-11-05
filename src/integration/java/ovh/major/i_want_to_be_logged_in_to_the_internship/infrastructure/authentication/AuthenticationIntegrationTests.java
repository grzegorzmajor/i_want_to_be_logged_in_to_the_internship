package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import io.restassured.http.ContentType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationIntegrationTests extends IntegrationTestConfiguration {

    private static String confirmationLink;
    protected static String token;

    @Test
    @Order(1)
    void shouldGivenStatus401AndBadCredentialsMessageWhenUserTriesLoginWithBadCredentials() {
        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(Examples.BAD_CREDENTIALS)
        .when()
            .post("/login")
        .then()
            .statusCode(401)
            .and()
            .assertThat()
                .body("message",is(equalTo("Bad Credentials")))
            .and()
            .assertThat()
                .body("status",is(equalTo("UNAUTHORIZED")));
    }

    @Test
    @Order(2)
    void shouldGivenStatus201AndReturnUserDetailsInResponseBodyWhenUserTriesRegisterWithValidUserDetails() {
        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(Examples.REGISTER_CORRECT_CREDENTIALS)
        .when()
            .post("/register")
        .then()
            .statusCode(201)
            .and()
            .assertThat()
                .body("username",is(equalTo("user")))
            .and()
            .assertThat()
                .body("email",is(equalTo("john@do.com")))
            .and()
            .assertThat()
                .body("id", is(greaterThan(0)))
            .and()
            .assertThat()
                .body("registered",is(equalTo(true)));
    }
    
    @Test
    @Order(3)
    void shouldConfirmationEmailHasBeSentWhenUserRegistered() {
        confirmationLink = extractLinkFromMailBody(
        given()
            .baseUri("http://" + mailhogHost())
            .port(mailhogHttpPort())
            .basePath("/api/v2")
        .when()
            .get("/messages")
        .then()
            .assertThat()
                .body("total", is(equalTo(1)))
            .and()
            .assertThat()
                .body("items[0].Content.Body",containsString("Email confirmation"))
            .and()
            .assertThat()
                .body("items[0].Content.Headers.To[0]",is(equalTo("john@do.com")))
        .and()
        .extract()
        .response()
        .path("items[0].Content.Body"));

        assertThat(confirmationLink,containsString("confirm"));
    }

    @Test
    @Order(4)
    void shouldGivenStatus401AndMessageNotConfirmedWhenRegisteredUserTriesToLoginWithoutEmailConfirmation() {
        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(Examples.CORRECT_CREDENTIALS)
        .when()
            .post("/login")
        .then()
            .statusCode(401)
            .and()
            .assertThat()
                .body("message",is(equalTo("Email is not confirmed!")))
            .and()
            .assertThat()
                .body("status",is(equalTo("UNAUTHORIZED")));
    }

    @Test
    @Order(5)
    void shouldGivenTextResponseWhenUserTriesConfirmEmailByLinkFromEmail() {
        given()
            .port(port)
        .when()
            .get(confirmationLink)
        .then()
            .statusCode(200)
            .body(containsString("Email Confirmation"));
    }
    @Test
    @Order(6)
    void shouldGivenStatus200AndTokenWhenUserTriesLoginAndEmailHasBeenConfirmed() {
        token =
        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(Examples.CORRECT_CREDENTIALS)
        .when()
            .post("/login")
            .then()
            .statusCode(200)
            .and()
            .assertThat()
                    .body("username",is(equalTo("user")))
        .and()
        .extract()
        .response()
        .path("token");
    }

    private String extractLinkFromMailBody(@NotNull String input) {
        String prefix = "/confirm/";
        String suffix = "\"";
        int startIndex = input.indexOf(prefix);
        int endIndex = input.indexOf(suffix, startIndex + prefix.length());
        if (startIndex != -1 && endIndex != -1) {
            String output = input.substring(startIndex, endIndex);
            return output;
        }
        return "";
    }

}
