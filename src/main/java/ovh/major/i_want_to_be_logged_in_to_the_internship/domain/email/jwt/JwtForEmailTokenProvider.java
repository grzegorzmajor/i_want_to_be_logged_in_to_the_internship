package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.*;

@AllArgsConstructor
@Component
public class JwtForEmailTokenProvider {

    private final JwtForEmailConfigurationProperties properties;
    private final Clock clock;

    public String generateToken(String username) {
        String secretKey = properties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofSeconds(properties.expirationSeconds()));
        String issuer = properties.issuer();
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsernameFromToken(String token) {
        String secretKey = properties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }



}
