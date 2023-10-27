package ovh.major.i_want_to_be_logged_in_to_the_internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.configurations.JwtConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})
public class IWantToBeLoggedInToTheInternshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(IWantToBeLoggedInToTheInternshipApplication.class, args);
    }

}
