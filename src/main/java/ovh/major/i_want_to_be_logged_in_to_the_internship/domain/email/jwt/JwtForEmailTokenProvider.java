package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;

import java.time.*;

@Component
public class JwtForEmailTokenProvider {
    public JwtForEmailTokenProvider(
            @Qualifier("jwt.email-ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt.JwtForEmailConfigurationProperties")
            JwtForEmailConfigurationProperties properties,
            Clock clock) {
        this.properties = properties;
        this.clock = clock;
    }

    private final JwtForEmailConfigurationProperties properties;
    private final Clock clock;

    public String generateToken(UserForEmailDto userForEmailDto) throws JsonProcessingException {
        String secretKey = properties.getSecret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofSeconds(properties.expirationSeconds));
        String issuer = properties.getIssuer();
        ObjectMapper objectMapper = new ObjectMapper();
        String json =  objectMapper.writeValueAsString(userForEmailDto);

        return JWT.create()
                .withSubject(json)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public UserForEmailDto getDtoFromToken(String token) throws JsonProcessingException {
        String secretKey = properties.getSecret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        String json = jwt.getSubject();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, UserForEmailDto.class);
    }



}
