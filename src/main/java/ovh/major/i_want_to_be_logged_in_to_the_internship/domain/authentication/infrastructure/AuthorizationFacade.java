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
    UpdateService updateService;

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

    @Transactional
    public void updateUsernameByOldUsername(String oldUsername, String newUsername) {
        updateService.updateUsernameByOldUsername(oldUsername,newUsername);
    }
    @Transactional
    public void updateEmailByUsername(String username, String newEmail) {
        updateService.updateEmailByUsername(username,newEmail);
    }
    @Transactional
    public void updatePasswordByUsername(String username, String password) {
        updateService.updatePasswordByUsername(username, password);
    }
}
