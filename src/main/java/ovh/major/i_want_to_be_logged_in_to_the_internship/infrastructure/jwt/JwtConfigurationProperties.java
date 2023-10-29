package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
record JwtConfigurationProperties(
        @Value("auth.jwt.auth.secret")
        String secret,
        @Value("auth.jwt.auth.expirationSeconds")
        long expirationSeconds,
        @Value("auth.jwt.auth.issuer")
        String issuer
) {
}
