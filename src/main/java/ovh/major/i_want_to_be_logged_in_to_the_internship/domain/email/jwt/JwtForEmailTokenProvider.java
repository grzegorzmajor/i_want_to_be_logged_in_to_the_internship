package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class JwtForEmailTokenProvider {
    public JwtForEmailTokenProvider(
            @Qualifier("auth.jwt.email-ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailConfigurationProperties")
            JwtForEmailConfigurationProperties properties,
            Clock clock) {
        this.properties = properties;
        this.clock = clock;
    }

    private final JwtForEmailConfigurationProperties properties;
    private final Clock clock;

    public String generateToken(String username) {
        String secretKey = properties.getSecret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofSeconds(properties.expirationSeconds));
        String issuer = properties.getIssuer();
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsernameFromToken(String token) {
        String secretKey = properties.getSecret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }



}
