package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import org.junit.jupiter.api.Test;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;

import static org.junit.jupiter.api.Assertions.*;

public class UserMappersTest {

    @Test
    void shouldHaveSameDataWhenMapFromUserEntityToUserDtoTest() {
        //given
        UserEntity source = UserEntity.builder()
                .email("user@svr.pl")
                .password("pass")
                .username("name")
                .emailAuthenticated(true)
                .id(0)
                .build();
        //when
        UserDto result = UserMappers.fromUserEntityToUserDto(source);

        //then
        assertAll(
                () -> assertEquals(source.getUsername(), result.username()),
                () -> assertEquals(source.getPassword(), result.password())
        );
    }

    @Test
    void shouldHaveSameDataWhenMapFromUserRegisterRequestDtoToUserEntityTest() {
        //given
        UserRegisterRequestDto source = UserRegisterRequestDto.builder()
                .email("user@svr.pl")
                .password("pass")
                .username("name")
                .build();
        //when
        UserEntity result = UserMappers.fromUserRegisterRequestDtoToUserEntity(source);

        //then
        assertAll(
                () -> assertEquals(source.email(), result.getEmail()),
                () -> assertEquals(source.password(), result.getPassword()),
                () -> assertEquals(source.username(), result.getUsername())
        );
    }

    @Test
    void shouldHaveSameDataWhenMapFromUserEntityToRegistrationResultDtoTest() {
        //given
        UserEntity source = UserEntity.builder()
                .email("user@svr.pl")
                .password("pass")
                .username("name")
                .emailAuthenticated(true)
                .id(0)
                .build();
        //when
        RegistrationResultDto result = UserMappers.fromUserEntityToRegistrationResultDto(source);

        //then
        assertAll(
                () -> assertEquals(source.getId(), result.id()),
                () -> assertEquals(source.getUsername(), result.username()),
                () -> assertEquals(source.getEmail(), result.email()),
                () -> assertTrue(result.registered())
        );
    }

    @Test
    void shouldHaveSameDataWhenMapFromUserEntityToUserForEmailDtoTest() {
        //given
        UserEntity source = UserEntity.builder()
                .email("user@svr.pl")
                .password("pass")
                .username("name")
                .emailAuthenticated(true)
                .id(0)
                .build();
        //when
        UserForEmailDto result = UserMappers.fromUserEntityToUserForEmailDto(source);

        //then
        assertAll(
                () -> assertEquals(source.getUsername(), result.username()),
                () -> assertEquals(source.getEmail(), result.email())
        );
    }
}
