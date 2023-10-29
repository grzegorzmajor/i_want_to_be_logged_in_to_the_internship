package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender.EmailSenderFacade;

@Service
class RegistrationService {

    UserRepository userRepository;
    EmailSenderFacade emailSenderFacade;

    @Transactional
    public RegistrationResultDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        UserEntity userEntity = UserMappers.fromUserRegisterRequestDtoToUserEntity(userRegisterRequestDto);
        UserEntity savedResult = userRepository.saveUser(userEntity);
        UserForEmailDto userForEmailDto = UserMappers.fromUserEntitytoUserForEmailDto(savedResult);
        emailSenderFacade.sendConfirmationEmail(userForEmailDto);
        return UserMappers.fromUserEntityToRegistrationResultDto(savedResult);
    }
}
