package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserLoginRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.TokenResponseDto;

import java.time.*;

@Component
public class JwtAuthenticator {

    private final AuthenticationManager authenticationManager;
    private final Clock clock;
    private final JwtConfigurationProperties properties;

    public JwtAuthenticator(
            AuthenticationManager authenticationManager,
            Clock clock,
            @Qualifier("auth.jwt.auth-ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt.JwtConfigurationProperties")
            JwtConfigurationProperties properties) {
        this.authenticationManager = authenticationManager;
        this.clock = clock;
        this.properties = properties;
    }

    public TokenResponseDto authenticateAndGenerateToken(UserLoginRequestDto userLoginRequestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequestDto.username(), userLoginRequestDto.password()));
        User user = (User) authenticate.getPrincipal();
        String token = createToken(user);
        String name = user.getUsername();
        return TokenResponseDto.builder()
                .token(token)
                .username(name)
                .build();
    }

    private String createToken(User user) {
        String secretKey = properties.getSecret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofSeconds(properties.getExpirationSeconds()));
        String issuer = properties.getIssuer();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

}
