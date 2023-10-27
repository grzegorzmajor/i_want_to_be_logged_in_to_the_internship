package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.LoginFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;

import java.util.Collections;

@AllArgsConstructor
class LoginUserDetailsService implements UserDetailsService {

    private final LoginFacade loginFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        UserDto userDto = loginFacade.findByName(username);
        return getUser(userDto);
    }

    private org.springframework.security.core.userdetails.User getUser(UserDto user) {
        return new org.springframework.security.core.userdetails.User(
                user.username(),
                user.password(),
                Collections.emptyList());
    }
}