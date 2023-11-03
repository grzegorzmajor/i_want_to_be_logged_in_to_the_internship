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

    //TODO wyłuskać oldUserName z tokena, lub sprawdzać czy jest takie same jak w tokenie.
    // User nie może zmieniać innych uzerów.


    UserRepository repository;
    EmailSenderFacade emailSenderFacade;

    void updateUsernameByOldUsername(String oldUsername, String newUsername) {
        throwWhenUsernameExistInDb(oldUsername);
        if (repository.existsUserEntityByUsername(newUsername)) {
            throw new DuplicateCredentialsException();
        }
        repository.updateUsernameByUsername(oldUsername, newUsername);
        UserEntity updateResult = repository.findByUsername(newUsername);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(updateResult);
        emailSenderFacade.sendSecurityInformationEmail(userForEmailDto, "Your username has bean changed!");
    }

    void updateEmailByUsername(String username, String newEmail) {
        throwWhenUsernameExistInDb(username);
        if (repository.existsUserEntityByEmail(newEmail)) {
            throw new DuplicateCredentialsException();
        }
        repository.updateEmailByUsername(username,newEmail);
        UserEntity updateResult = repository.findByUsername(username);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(updateResult);
        emailSenderFacade.sendConfirmationEmail(userForEmailDto);
    }

    void updatePasswordByUsername(String username, String password) {
        throwWhenUsernameExistInDb(username);
        repository.updatePasswordByUsername(username,password);
        UserEntity updateResult = repository.findByUsername(username);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(updateResult);
        emailSenderFacade.sendSecurityInformationEmail(userForEmailDto, "Your password has bean changed!");
    }

    private void throwWhenUsernameExistInDb(String oldUsername) {
        if (!repository.existsUserEntityByUsername(oldUsername)) {
            throw new UsernameNotFoundException(USER_NOT_FOUND.toString());
        }
    }




}
