package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.TokenResponseDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserLoginRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt.JwtAuthenticator;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
class LoginController {

    private final JwtAuthenticator jwtAuthenticatorFacade;

    @PostMapping
    public ResponseEntity<TokenResponseDto> authenticateAndGenerateToken(@RequestBody @Valid UserLoginRequestDto tokenRequestDto) {
        final TokenResponseDto tokenResponseDto = jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequestDto);
        return ResponseEntity.ok(tokenResponseDto);
    }


}
