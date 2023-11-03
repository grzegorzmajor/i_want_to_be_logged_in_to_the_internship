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
    private final static String EMAIL_SECURITY_INFORMATION_SUBJECT = "Security information from The Internship Application.";
    private final static String APPLICATION_LINK = "http://localhost:8888/";


    public void sendConfirmationEmail(UserForEmailDto user) {
        emailSenderService.sendEmail(
                user.email(),
                EMAIL_CONFIRMATION_SUBJECT,
                getConfirmationEmailContentWithLink(user.username())
        );
    }

    public void sendSecurityInformationEmail(UserForEmailDto user, String message) {
        emailSenderService.sendEmail(
                user.email(),
                EMAIL_SECURITY_INFORMATION_SUBJECT,
                getSecurityEmailContent(user.username(),message)
        );
    }

    private String getConfirmationEmailContentWithLink(String username) {
        return getEmailWithLink(
                "Email confirmation!",
                "You have registered in The Internship Application. If you registered," +
                        "you must confirm your email. Click on the link below or copy and paste it into your browser.",
                APPLICATION_LINK + "confirm/" + tokenProvider.generateToken(username),
                "The link will expire after 5 minutes!",
                username);
    }
    private String getSecurityEmailContent(String username, String message) {
        return getEmailWithoutLink(
                "Security message!",
                message,
                username);
    }

    private String getEmailWithLink(String tittle,
                                    String textBefore,
                                    String link,
                                    String textAfter,
                                    String username) {
        return EmailWithLinkContent.builder()
                .title(tittle)
                .textBeforeLink("Hi, " + username + "! " + textBefore)
                .link(link)
                .textAfterLink(textAfter)
                .build()
                .toString();
    }
    private String getEmailWithoutLink(String tittle,
                                    String text,
                                    String username) {
        return EmailWithoutLinkContent.builder()
                .title(tittle)
                .text("Hi, " + username + "! " + text)
                .build()
                .toString();
    }


}
