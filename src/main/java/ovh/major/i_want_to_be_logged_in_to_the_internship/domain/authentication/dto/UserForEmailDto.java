package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto;

import javax.validation.constraints.NotBlank;

public record UserForEmailDto(
        @NotBlank(message = "Username must be not blank!")
        String username,

        @NotBlank(message = "email must be not blank!")
        String email
) { }
