package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.ExceptionMessages;

@Service
@AllArgsConstructor
class DeleteService {

    UserRepository repository;

    void deleteByUsername(String username){
        UserEntity user = repository.findByUsername(username);
        if (!user.getUsername()
                .equals(username)) {
            throw new UsernameNotFoundException(ExceptionMessages.USER_NOT_FOUND.toString());
        }
        repository.deleteUserByUsername(username);
    }

}
