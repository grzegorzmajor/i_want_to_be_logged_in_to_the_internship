package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ovh.major.i_want_to_be_logged_in_to_the_internship.IWantToBeLoggedInToTheInternshipApplication;

import static io.restassured.config.EncoderConfig.encoderConfig;

@SpringBootTest(classes = IWantToBeLoggedInToTheInternshipApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class IntegrationTestConfiguration {

    private static final Integer PORT_SMTP = 1025;
    private static final Integer PORT_HTTP = 8025;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

    @LocalServerPort
    protected int port;

    @Container
    public static GenericContainer<?> mailhog = new GenericContainer<>("mailhog/mailhog")
            .withExposedPorts(PORT_SMTP, PORT_HTTP);

    @BeforeAll
    public static void beforeAll() {
        //database
        postgreSQLContainer.start();
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());

        //Restassured
        RestAssured.baseURI = "http://localhost";
        RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

        //email
        String mailhogHost = mailhog.getHost();
        int mailhogSmtpPort = mailhog.getMappedPort(1025);
        System.setProperty("spring.mail.host", mailhogHost);
        System.setProperty("spring.mail.port", String.valueOf(mailhogSmtpPort));
        System.setProperty("spring.mail.user", "john.doe");
        System.setProperty("spring.mail.pass", "s3cr3t");

        ///other properties
        System.setProperty("jwt.auth.expirationSeconds", "200");
        System.setProperty("jwt.auth.secret", "secret");
        System.setProperty("jwt.email.expirationSeconds", "200");
        System.setProperty("jwt.email.secret", "secret");

    }

    protected String mailhogHost() {
        return mailhog.getHost();
    }

    int mailhogSmtpPort() {
        return mailhog.getMappedPort(PORT_SMTP);
    }

    protected int mailhogHttpPort() {
        return mailhog.getMappedPort(PORT_HTTP);
    }

    @AfterAll
    public static void tearDown() {
        postgreSQLContainer.stop();
        mailhog.stop();
    }

}
