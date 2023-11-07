package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.UserDetailNotAcceptedException;

import static ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;

@Service
@AllArgsConstructor
class UpdateService {

    UserRepository repository;

    void updateUsernameByOldUsername(String oldUsername, String newUsername) {
        throwWhenUsernameNoExistInDb(oldUsername);
        if (repository.existsUserEntityByUsername(newUsername)) {
            throw new UserDetailNotAcceptedException("username");
        }
        repository.updateUsernameByUsername(oldUsername, newUsername);
    }

    void updateEmailByUsername(String username, String newEmail) {
        throwWhenUsernameNoExistInDb(username);
        if (repository.existsUserEntityByEmail(newEmail)) {
            throw new UserDetailNotAcceptedException("email");
        }
        repository.updateEmailByUsername(username,newEmail);
    }

    void updatePasswordByUsername(String username, String password) {
        throwWhenUsernameNoExistInDb(username);
        repository.updatePasswordByUsername(username,password);
    }

    private void throwWhenUsernameNoExistInDb(String oldUsername) {
        if (!repository.existsUserEntityByUsername(oldUsername)) {
            throw new UsernameNotFoundException(USER_NOT_FOUND.toString());
        }
    }




}
