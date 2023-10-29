package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;

@Component
@AllArgsConstructor
public class AuthorizationFacade {
    //todo please implement me

    LoginService loginService;
    RegistrationService registrationService;

    public void emailConfirmation(String username) {
        loginService.emailConfirmation(username);
    }

    public UserDto findByUsername(String username) {
        return loginService.findByUsername(username);
    }

    public RegistrationResultDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        return registrationService.registerUser(userRegisterRequestDto);
    }
}
