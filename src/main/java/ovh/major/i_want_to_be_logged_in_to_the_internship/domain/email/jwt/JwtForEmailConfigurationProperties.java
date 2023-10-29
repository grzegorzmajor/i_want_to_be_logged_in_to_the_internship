package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
record JwtForEmailConfigurationProperties (
            @Value("auth.jwt.email.secret")
            String secret,
            @Value("auth.jwt.email.expirationSeconds")
            long expirationSeconds,
            @Value("auth.jwt.email.issuer")
            String issuer
){}
