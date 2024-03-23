package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto;

import lombok.Builder;

import jakarta.validation.constraints.NotBlank;

@Builder
public record UserLoginRequestDto(

        @NotBlank(message = "Username must be not blank!")
        String username,

        @NotBlank(message = "password must be not blank!")
        String password
) {
}
