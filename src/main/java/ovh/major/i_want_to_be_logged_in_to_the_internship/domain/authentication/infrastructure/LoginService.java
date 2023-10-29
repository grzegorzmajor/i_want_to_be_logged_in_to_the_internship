package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;

@Service
class LoginService {

    UserRepository repository;

    @Transactional
    public void emailConfirmation(String username) {
        repository.emailAuthenticateByUsername(username);
    }

    public UserDto findByUsername(String username) {
        UserEntity userEntity = repository.findByUsername(username);
        UserDto userDto = UserMappers.fromUserEntitytoUserDto(userEntity);
        return userDto;
    }
}
