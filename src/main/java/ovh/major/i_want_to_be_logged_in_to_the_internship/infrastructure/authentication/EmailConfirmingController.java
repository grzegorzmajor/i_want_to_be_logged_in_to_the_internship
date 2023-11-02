package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;

@Controller
class EmailConfirmingController {

    JwtForEmailTokenProvider tokenProvider;
    AuthorizationFacade authorizationFacade;
    private String txt = "";

    public EmailConfirmingController(JwtForEmailTokenProvider tokenProvider, AuthorizationFacade authorizationFacade) {
        this.tokenProvider = tokenProvider;
        this.authorizationFacade = authorizationFacade;
    }

    @GetMapping("/confirm/{token}")
    public String confirm(Model model, @PathVariable String token) {
        try {
            String username = tokenProvider.getUsernameFromToken(token);
            authorizationFacade.confirmEmail(username);
            txt = "Your email was confirmed!";
        } catch (TokenExpiredException e) {
            txt = "I`m Sorry, your late! The link was expired!";
        }
        model.addAttribute("txt", txt );
        return "emailConfirmation.html";

    }

}
