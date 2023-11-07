package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions;

public class UserDetailNotAcceptedException extends RuntimeException {
    public UserDetailNotAcceptedException(String message) {
        super("Your new " + message + " is not accepted! ");
    }
}
