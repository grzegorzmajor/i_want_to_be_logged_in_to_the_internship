package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;

class UserMapper {
    public static UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }
}
