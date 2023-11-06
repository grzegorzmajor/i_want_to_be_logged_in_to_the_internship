package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(value = "jwt.auth")
@Getter
@Setter
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})
class JwtConfigurationProperties {
        String secret;
        long expirationSeconds;
        String issuer;
}
