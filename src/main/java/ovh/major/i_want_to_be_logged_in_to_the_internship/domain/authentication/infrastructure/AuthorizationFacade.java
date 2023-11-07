package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
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
        return UserMappers.fromUserEntityToUserDto(loginService.findByUsername(username));
    }
    public String findEmailByUsername(String username) {
        return loginService.findEmailByUsername(username);
    }

    public RegistrationResultDto registerUser(UserRegisterRequestDto userRegisterRequestDto) throws JsonProcessingException {
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

    public Integer findUserIdByUsername(String username) {
        UserEntity user = loginService.findByUsername(username);
        return user.getId();
    }

    public UserForEmailDto findUserById(Integer id) {
        UserEntity user = loginService.findById(id);
        return UserMappers.fromUserEntityToUserForEmailDto(user);
    }
}
