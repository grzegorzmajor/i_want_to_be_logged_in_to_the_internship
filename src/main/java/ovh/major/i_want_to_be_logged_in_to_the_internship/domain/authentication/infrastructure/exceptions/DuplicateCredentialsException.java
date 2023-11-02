package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions;

public class DuplicateCredentialsException extends RuntimeException {
    public DuplicateCredentialsException() {
        super(ExceptionMessages.USER_EXISTS.toString());
    }
}
