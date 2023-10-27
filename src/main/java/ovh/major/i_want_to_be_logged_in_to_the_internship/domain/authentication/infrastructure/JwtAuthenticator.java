package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.configurations.JwtConfigurationProperties;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserLoginRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserResponseDto;


import java.time.*;

@AllArgsConstructor
@Component
class JwtAuthenticator {

    private final AuthenticationManager authenticationManager;
    private final Clock clock;
    private final JwtConfigurationProperties properties;

    public UserResponseDto authenticateAndGenerateToken(UserLoginRequestDto userLoginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequestDto.username(), userLoginRequestDto.password()));
        UserEntity user = (UserEntity) authenticate.getPrincipal();
        String token = createToken(user);
        String name = user.getUsername();
        return UserResponseDto.builder()
                .token(token)
                .username(name)
                .build();
    }

    private String createToken(UserEntity user) {
        String secretKey = properties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofDays(properties.expirationDays()));
        String issuer = properties.issuer();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

}
