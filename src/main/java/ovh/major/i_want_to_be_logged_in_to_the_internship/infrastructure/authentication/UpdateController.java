package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UpdateRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UserForEmailDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender.EmailSenderFacade;
import ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error.NoPermissionException;
import ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.jwt.JwtAuthTokenFilter;

@RestController
@AllArgsConstructor
@RequestMapping("/update")
public class UpdateController {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthorizationFacade authorizationFacade;
    private final JwtAuthTokenFilter jwtAuthTokenFilter;
    private final EmailSenderFacade emailSenderFacade;

    @PatchMapping("/{oldUsername}")
    public void updateUsername(@PathVariable("oldUsername") String oldUsername, @RequestBody UpdateRequestDto updateRequestDto, @RequestHeader("Authorization") String token) throws JsonProcessingException {
        StringBuilder updatedUserDetails = new StringBuilder();
        String actualUsername = oldUsername;
        throwIfThereIsNoPermissionToChangeUserData(oldUsername, token);

        if (updateRequestDto.password() != null) {
            changePassword(oldUsername, updateRequestDto, updatedUserDetails); }

        if (updateRequestDto.username() != null) {
            actualUsername = changeUsernameIfRequired(oldUsername, updateRequestDto, updatedUserDetails) ?
                    updateRequestDto.username() : oldUsername; }

        if (updateRequestDto.email() != null) {
            sendEmailConfirmationEmail(actualUsername, updateRequestDto); }

        if (updatedUserDetails.length() >0) {
            emailSenderFacade.sendSecurityInformationEmail(
                UserForEmailDto.builder()
                    .username(actualUsername)
                    .email(updateRequestDto.email() == null ?
                            authorizationFacade.findEmailByUsername(actualUsername) : updateRequestDto.email())
                    .build(),
                updatedUserDetails.toString());
        }
    }

    private void sendEmailConfirmationEmail(String actualUsername, UpdateRequestDto updateRequestDto) throws JsonProcessingException {
        String oldEmail = authorizationFacade
                .findByUsername(actualUsername)
                .email();

        if (!oldEmail.equals(updateRequestDto.email())) {
            emailSenderFacade.sendConfirmationEmail(new UserForEmailDto(
                    updateRequestDto.username() == null ? actualUsername : updateRequestDto.username(),
                    updateRequestDto.email()));
        }
    }

    private boolean changeUsernameIfRequired(String oldUsername, UpdateRequestDto updateRequestDto, StringBuilder updatedUserDetails) {
        if (!oldUsername.equals(updateRequestDto.username())) {
            authorizationFacade.updateUsernameByOldUsername(oldUsername, updateRequestDto.username());
            appendCommaIfRequired(updatedUserDetails);
            updatedUserDetails.append("username");
            return true;
        }
        return false;
    }

    private void changePassword(String oldUsername, UpdateRequestDto updateRequestDto, StringBuilder updatedUserDetails) {
        String encodedPassword = bCryptPasswordEncoder.encode(updateRequestDto.password());
        authorizationFacade.updatePasswordByUsername(oldUsername, encodedPassword);
        appendCommaIfRequired(updatedUserDetails);
        updatedUserDetails.append("password");
    }

    private void throwIfThereIsNoPermissionToChangeUserData(String oldUsername, String token) {
        if (!jwtAuthTokenFilter.isContainUsername(token, oldUsername)) {
            throw new NoPermissionException();
        }
    }

    private static void appendCommaIfRequired(StringBuilder updatedUserDetails) {
        if (updatedUserDetails.length() > 0) {
            updatedUserDetails.append(", ");
        }
    }
}
