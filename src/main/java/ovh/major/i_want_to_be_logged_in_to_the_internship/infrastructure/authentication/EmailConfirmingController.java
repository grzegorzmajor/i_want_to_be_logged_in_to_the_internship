package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;

@RestController
@AllArgsConstructor
class EmailConfirmingController {

    JwtForEmailTokenProvider tokenProvider;
    AuthorizationFacade authorizationFacade;

    @GetMapping("/confirm/{token}")
    public void confirm(@PathVariable String token) {
        String username = tokenProvider.getUsernameFromToken(token);
        authorizationFacade.confirmEmail(username);
    }

}
