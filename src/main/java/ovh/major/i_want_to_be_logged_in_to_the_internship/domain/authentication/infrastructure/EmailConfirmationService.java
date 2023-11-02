package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class EmailConfirmationService {

    UserRepository repository;

    @Transactional
    public void confirm(String username) {
        repository.emailAuthenticateByUsername(username);
    }
}
