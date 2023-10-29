package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;

public class EmailSenderFacade {

    EmailSenderService emailSenderService;
    JwtForEmailTokenProvider tokenProvider;

    private final static String subject = "Email confirmation from The Internship Application.";


    public void sendConfirmationEmail(UserForEmailDto user) {
        emailSenderService.sendEmail(
                user.email(),
                subject,
                getConfirmationEmailWithLinkContent(user.username())
        );
    }

    private String getConfirmationEmailWithLinkContent(String username) {
        return EmailWithLinkContent.builder()
                .title("Email confirmation!")
                .textBeforeLink("Hi, " + username + "! You have registered in The Internship Application. If you registered," +
                        "you must confirm your email. Click on the link below or copy and paste it into your browser.")
                .link("https://localhost:76543/confirm/" + tokenProvider.generateToken(username))
                .textAfterLink("The link will expire after 5 minutes!")
                .build()
                .toString();
    }

}
