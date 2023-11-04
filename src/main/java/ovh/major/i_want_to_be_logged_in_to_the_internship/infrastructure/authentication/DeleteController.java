package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error.NoPermissionException;
import ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt.JwtAuthTokenFilter;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
class DeleteController {

    private final AuthorizationFacade authorizationFacade;
    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username, @RequestHeader("Authorization") String token) {
        if (!jwtAuthTokenFilter.isContainUsername(token,username)) {
            throw new NoPermissionException();
        }
        authorizationFacade.deleteUser(username);
    }

}
