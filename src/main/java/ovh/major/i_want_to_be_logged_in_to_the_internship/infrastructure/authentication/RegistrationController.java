package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.RegistrationResultDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserRegisterRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;

@RestController
@AllArgsConstructor
@RequestMapping("/register")
class RegistrationController {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthorizationFacade authorizationFacade;

    @PostMapping
    public ResponseEntity<RegistrationResultDto> registerUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) throws JsonProcessingException {
        String encodedPassword = bCryptPasswordEncoder.encode(userRegisterRequestDto.password());
        RegistrationResultDto registrationResultDto = authorizationFacade.registerUser(
                UserRegisterRequestDto.builder()
                        .username(userRegisterRequestDto.username())
                        .password(encodedPassword)
                        .email(userRegisterRequestDto.email())
                        .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResultDto);

    }
}
