package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error;

import static ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error.ExceptionMessages.NO_PERMISSION;

public class NoPermissionException extends RuntimeException  {
    public NoPermissionException() {
        super (NO_PERMISSION.toString());
    }
}
