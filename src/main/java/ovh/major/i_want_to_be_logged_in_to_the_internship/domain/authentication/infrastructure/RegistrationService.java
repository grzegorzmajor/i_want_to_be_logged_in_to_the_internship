package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.exceptions.DuplicateCredentialsException;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender.EmailSenderFacade;

@Service
@AllArgsConstructor
class RegistrationService {

    UserRepository userRepository;
    EmailSenderFacade emailSenderFacade;

    @Transactional
    public RegistrationResultDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        UserEntity userEntity = UserMappers.fromUserRegisterRequestDtoToUserEntity(userRegisterRequestDto);
        if ( userRepository.existsUserEntityByUsernameOrEmail(userEntity.getUsername(), userEntity.getEmail()) ) {
            throw new DuplicateCredentialsException();
        };
        UserEntity savedResult = userRepository.save(userEntity);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntityToUserForEmailDto(savedResult);
        emailSenderFacade.sendConfirmationEmail(userForEmailDto);
        return UserMappers.fromUserEntityToRegistrationResultDto(savedResult);
    }
}
