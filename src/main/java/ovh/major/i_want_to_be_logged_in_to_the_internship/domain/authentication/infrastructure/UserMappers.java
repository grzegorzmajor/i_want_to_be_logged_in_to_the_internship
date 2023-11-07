package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;

class UserMappers {
    public static UserDto fromUserEntityToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .build();
    }

    public static UserEntity fromUserRegisterRequestDtoToUserEntity(UserRegisterRequestDto userRegisterRequestDto) {
        return UserEntity.builder()
                .username(userRegisterRequestDto.username())
                .password(userRegisterRequestDto.password())
                .email(userRegisterRequestDto.email())
                .emailAuthenticated(false)
                .build();
    }

    public static RegistrationResultDto fromUserEntityToRegistrationResultDto(UserEntity userEntity) {
        return RegistrationResultDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .registered(true)
                .build();

    }

    public static UserForEmailDto fromUserEntityToUserForEmailDto(UserEntity savedResult) {
        return UserForEmailDto.builder()
                .username(savedResult.getUsername())
                .email(savedResult.getEmail())
                .build();
    }
}
