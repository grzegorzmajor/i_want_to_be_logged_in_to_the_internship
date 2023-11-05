package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template;

import lombok.Builder;

@Builder
public record Email(
        String subject,
        String body
) { }
