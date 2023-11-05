package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template.EmailTemplate;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template.EmailTemplateFacade;

@Component
@AllArgsConstructor
public class EmailSenderFacade {

    EmailSenderService emailSenderService;
    JwtForEmailTokenProvider tokenProvider;

    public void sendConfirmationEmail(UserForEmailDto user) {
        EmailTemplate emailTemplate = EmailTemplateFacade.confirmationEmail("confirm/" + tokenProvider.generateToken(user.username()), user.username());
        emailSenderService.sendEmail(
                user.email(),
                emailTemplate.subject(),
                emailTemplate.body()
        );
    }

    public void sendSecurityInformationEmail(UserForEmailDto user, String updated) {
        EmailTemplate emailTemplate = EmailTemplateFacade.securityInformationEmail(updated,user.username());
        emailSenderService.sendEmail(
                user.email(),
                emailTemplate.subject(),
                emailTemplate.body()
        );
    }
}