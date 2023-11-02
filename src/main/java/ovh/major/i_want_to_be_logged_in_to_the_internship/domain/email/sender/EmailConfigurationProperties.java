package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(value = "spring.mail")
@Getter
@Setter
class EmailConfigurationProperties {
        String host;
        int port;
        String username;
        String password;
}
