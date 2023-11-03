package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.DuplicateCredentialsException;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.EmailNotConfirmedException;

import static ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.ExceptionMessages.*;


@ControllerAdvice
class ControllerErrorHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ErrorResponse handleBadCredentials() {
        return new ErrorResponse(BAD_CREDENTIALS.toString(), HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateCredentialsException.class)
    @ResponseBody
    public ErrorResponse handleDuplicateCredentails() {
        return new ErrorResponse(USER_EXISTS.toString(), HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(EmailNotConfirmedException.class)
    @ResponseBody
    public ErrorResponse handleEmailNotConfirmed() {
        return new ErrorResponse(EMAIL_NOT_CONFIRMED.toString(), HttpStatus.UNAUTHORIZED);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleUsernameNotFound() {
        return new ErrorResponse(USER_NOT_FOUND.toString(), HttpStatus.NOT_FOUND);
    }
}
