package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;

@Component
@AllArgsConstructor
public class LoginFacade {
    //todo please implement me

    UserRepository repository;

    @Transactional
    public void emailConfirmation(String username) {
        repository.emailAuthenticateByUsername(username);
    }

    public UserDto findByUsername(String username) {
        UserEntity userEntity = repository.findByUsername(username);
        UserDto userDto = UserMapper.toUserDto(userEntity);
        return userDto;
    }
}
