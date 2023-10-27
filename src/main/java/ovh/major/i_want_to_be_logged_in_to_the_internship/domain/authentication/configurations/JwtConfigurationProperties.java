package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "auth.jwt")
public record JwtConfigurationProperties(
        String secret,
        long expirationDays,
        String issuer
) {
}
