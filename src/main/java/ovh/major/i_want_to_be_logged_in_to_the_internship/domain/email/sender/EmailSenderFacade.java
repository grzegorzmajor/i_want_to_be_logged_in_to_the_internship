package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template.Email;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template.EmailComposerFacade;

@Component
@AllArgsConstructor
public class EmailSenderFacade {

    EmailSenderService emailSenderService;
    JwtForEmailTokenProvider tokenProvider;

    public void sendConfirmationEmail(UserForEmailDto user) {
        Email email = EmailComposerFacade.confirmationEmail("confirm/" + tokenProvider.generateToken(user.username()), user.username());
        emailSenderService.sendEmail(
                user.email(),
                email.subject(),
                email.body()
        );
    }

    public void sendSecurityInformationEmail(UserForEmailDto user, String updated) {
        Email email = EmailComposerFacade.securityInformationEmail(updated,user.username());
        emailSenderService.sendEmail(
                user.email(),
                email.subject(),
                email.body()
        );
    }
}