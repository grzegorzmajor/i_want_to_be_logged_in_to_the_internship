package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import org.springframework.beans.factory.annotation.Value;

record EmailConfigurationProperties(
        @Value("${spring.mail.host}")
        String host,
        @Value("${spring.mail.port}")
        int port,
        @Value("${spring.mail.username}")
        String username,
        @Value("${spring.mail.password}")
        String password
) {}
