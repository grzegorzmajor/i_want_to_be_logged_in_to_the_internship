package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.DuplicateCredentialsException;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender.EmailSenderFacade;

import static ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.ExceptionMessages.USER_NOT_FOUND;

@Service
@AllArgsConstructor
class UpdateService {

    UserRepository repository;
    EmailSenderFacade emailSenderFacade;

    void updateUsernameByOldUsername(String oldUsername, String newUsername) {
        throwWhenUsernameExistInDb(oldUsername);
        if (!repository.existsUserEntityByUsername(newUsername)) {
            throw new DuplicateCredentialsException();
        }
        UserEntity updateResult = repository.updateUsernameByUsername(oldUsername, newUsername);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(updateResult);
        emailSenderFacade.sendSecurityInformationEmail(userForEmailDto, "Your username has bean changed!");
    }

    void updateEmailByUsername(String username, String newEmail) {
        throwWhenUsernameExistInDb(username);
        if (!repository.existsUserEntityByEmail(newEmail)) {
            throw new DuplicateCredentialsException();
        }
        UserEntity updateResult = repository.updateEmailByUsername(username,newEmail);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(updateResult);
        emailSenderFacade.sendConfirmationEmail(userForEmailDto);
    }

    void updatePasswordByUsername(String username, String password) {
        throwWhenUsernameExistInDb(username);
        UserEntity updateResult = repository.updatePasswordByUsername(username,password);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(updateResult);
        emailSenderFacade.sendSecurityInformationEmail(userForEmailDto, "Your password has bean changed!");
    }

    private void throwWhenUsernameExistInDb(String oldUsername) {
        if (!repository.existsUserEntityByUsername(oldUsername)) {
            throw new UsernameNotFoundException(USER_NOT_FOUND.toString());
        }
    }




}
