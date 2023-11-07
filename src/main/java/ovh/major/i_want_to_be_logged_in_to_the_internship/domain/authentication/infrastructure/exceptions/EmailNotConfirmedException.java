package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions;

public class EmailNotConfirmedException extends RuntimeException {
    public EmailNotConfirmedException() {
        super(ExceptionMessages.EMAIL_NOT_CONFIRMED.toString());
    }
}
