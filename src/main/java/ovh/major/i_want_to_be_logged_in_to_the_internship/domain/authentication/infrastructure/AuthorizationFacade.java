package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;

@Component
@AllArgsConstructor
public class AuthorizationFacade {
    LoginService loginService;
    EmailConfirmationService emailConfirmationService;
    RegistrationService registrationService;
    DeleteService deleteService;

    public void confirmEmail(String username) {
        emailConfirmationService.confirm(username);
    }

    public UserDto findByUsername(String username) {
        return loginService.findByUsername(username);
    }

    public RegistrationResultDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        return registrationService.registerUser(userRegisterRequestDto);
    }

    @Transactional
    public void deleteUser(String username) {
        deleteService.deleteByUsername(username);
    }
}
