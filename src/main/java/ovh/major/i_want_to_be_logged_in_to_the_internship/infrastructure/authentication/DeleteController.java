package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
class DeleteController {

    private final AuthorizationFacade authorizationFacade;

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        authorizationFacade.deleteUser(username);
    }

}
