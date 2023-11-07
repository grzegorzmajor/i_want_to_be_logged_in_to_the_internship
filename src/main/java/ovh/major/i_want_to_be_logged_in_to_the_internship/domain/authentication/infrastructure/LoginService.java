package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.EmailNotConfirmedException;

import static ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;

@Service
@AllArgsConstructor
class LoginService {

    private final UserRepository repository;

    public UserEntity findByUsername(String username) {
        UserEntity userEntity = repository
                .findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND.toString()));
        if (!userEntity.isEmailAuthenticated()) {
            throw new EmailNotConfirmedException();
        }
        return userEntity;
    }

    public String findEmailByUsername(String username) {
        UserEntity userEntity = repository
                .findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND.toString()));
        return userEntity.getEmail();
    }

    public UserEntity findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new BadCredentialsException(USER_NOT_FOUND.toString()));
    }
}
