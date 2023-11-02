package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.EmailNotConfirmedException;

import static ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;

@Service
@AllArgsConstructor
class LoginService {

    UserRepository repository;

    public UserDto findByUsername(String username) {
        UserEntity userEntity;
        try {
            userEntity = repository.findByUsername(username);
        }
        catch (RuntimeException e) {
            throw new BadCredentialsException(USER_NOT_FOUND.toString());
        }
        if (!userEntity.isEmailAuthenticated()) {
            throw new EmailNotConfirmedException();
        }
        return UserMappers.fromUserEntitytoUserDto(userEntity);
    }
}
