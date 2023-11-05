package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template;

public class EmailTemplateFacade {

    public static EmailTemplate confirmationEmail(String link, String username){
        return EmailTemplate.builder()
                .subject(EmailSubjects.EMAIL_CONFIRMATION.toString())
                .body(EmailContentTemplate.emailWithLink(
                        EmailMessages.TITLE_CONFIRMATION.toString(),
                        "Hi " +  username + "! " + EmailMessages.CONFIRMATION.toString(),
                        EmailMessages.LINK_URL.toString() + link,
                        EmailMessages.EXPIRATION_MESSAGE.toString()
                )).build();

    }
     public static EmailTemplate securityInformationEmail(String updated, String username){
        return EmailTemplate.builder()
                .subject(EmailSubjects.SECURITY_INFORMATION.toString())
                .body(EmailContentTemplate.email(EmailMessages.TITLE_SECURITY_INFORMATION.toString(),
                        "Hi " +  username + "! Your " + updated + EmailMessages.UPDATE_MESSAGE.toString()))
                .build();

    }

}