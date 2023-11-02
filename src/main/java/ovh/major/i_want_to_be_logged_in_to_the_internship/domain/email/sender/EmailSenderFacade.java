package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;

@Component
@AllArgsConstructor
public class EmailSenderFacade {

    EmailSenderService emailSenderService;
    JwtForEmailTokenProvider tokenProvider;

    private final static String EMAIL_CONFIRMATION_SUBJECT = "Email confirmation from The Internship Application.";
    private final static String APPLICATION_LINK = "http://localhost:8888/";


    public void sendConfirmationEmail(UserForEmailDto user) {
        emailSenderService.sendEmail(
                user.email(),
                EMAIL_CONFIRMATION_SUBJECT,
                getConfirmationEmailContentWithLink(user.username())
        );
    }

    private String getConfirmationEmailContentWithLink(String username) {
        return EmailWithLinkContent.builder()
                .title("Email confirmation!")
                .textBeforeLink("Hi, " + username + "! You have registered in The Internship Application. If you registered," +
                        "you must confirm your email. Click on the link below or copy and paste it into your browser.")
                .link(APPLICATION_LINK + "confirm/" + tokenProvider.generateToken(username))
                .textAfterLink("The link will expire after 5 minutes!")
                .build()
                .toString();
    }

}
