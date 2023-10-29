package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import org.springframework.stereotype.Service;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;

@Service
class RegistrationService {

    UserRepository userRepository;

    public RegistrationResultDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        UserEntity userEntity = UserMappers.fromUserRegisterRequestDtoToUserEntity(userRegisterRequestDto);
        UserEntity saveResult = userRepository.saveUser(userEntity);
        return UserMappers.fromUserEntityToRegistrationResultDto(saveResult);
    }
}
