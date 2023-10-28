package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt.JwtAuthenticator;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
class LoginController {

    private final JwtAuthenticator jwtAuthenticatorFacade;

}
