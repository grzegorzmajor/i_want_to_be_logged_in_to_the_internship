package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions;

public enum ExceptionMessages {
    USER_EXISTS("A user with that name or e-mail exists!"),
    USER_NOT_FOUND("User not found!"),
    EMAIL_NOT_CONFIRMED("Email is not confirmed!"),
    BAD_CREDENTIALS("Bad Credentials");

    private final String s;

    ExceptionMessages(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
