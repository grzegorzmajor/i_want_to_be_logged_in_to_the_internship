package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error;

import org.springframework.http.HttpStatus;

record ErrorResponse(String message, HttpStatus status) {
}
