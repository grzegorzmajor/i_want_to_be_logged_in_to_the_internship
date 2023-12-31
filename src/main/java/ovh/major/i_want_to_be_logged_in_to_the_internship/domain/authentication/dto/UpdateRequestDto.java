package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto;

import lombok.Builder;

@Builder
public record UpdateRequestDto(
        String username,
        String password,
        String email
) {
}
