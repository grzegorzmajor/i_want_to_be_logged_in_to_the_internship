package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication;


import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;

@Component
public class LoginFacade {
    public UserDto findByName(String username) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED!");
    }
}
