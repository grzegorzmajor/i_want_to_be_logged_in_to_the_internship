package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailTokenProvider;

@Controller
class EmailConfirmingController {

    JwtForEmailTokenProvider tokenProvider;
    AuthorizationFacade authorizationFacade;

    public EmailConfirmingController(JwtForEmailTokenProvider tokenProvider, AuthorizationFacade authorizationFacade) {
        this.tokenProvider = tokenProvider;
        this.authorizationFacade = authorizationFacade;
    }

    @GetMapping("/confirm/{token}")
    public String confirm(Model model, @PathVariable String token) {
        String txt = "";
        try {
            UserForEmailDto userForEmailDto = tokenProvider.getDtoFromToken(token);
            String inDbUserEmail = authorizationFacade.findEmailByUsername(userForEmailDto.username());
            String username = userForEmailDto.username();
            if (inDbUserEmail.equals(userForEmailDto.email())) {
                authorizationFacade.confirmEmail(username);
                txt = "Your email was confirmed!";
            } else {
                authorizationFacade.updateEmailByUsername(username, userForEmailDto.email());
                authorizationFacade.confirmEmail(username);
                txt = "Your email was changed and confirmed!";
            }
        } catch (TokenExpiredException e) {
            txt = "I`m Sorry, your late! The link was expired!";
        } catch (JsonProcessingException e) {
            txt = "Something is wrong with your link!";
        }
        model.addAttribute("txt", txt);
        return "emailConfirmation.html";

    }

}
