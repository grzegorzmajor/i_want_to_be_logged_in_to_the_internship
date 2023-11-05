package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UpdateRequestDto;
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
    public void updateUsername(@PathVariable("oldUsername") String oldUsername, @RequestBody UpdateRequestDto updateRequestDto, @RequestHeader("Authorization") String token) {
        StringBuilder updatedUserDetails = new StringBuilder();
        Integer id = authorizationFacade.findUserIdByUsername(oldUsername);
        if (!jwtAuthTokenFilter.isContainUsername(token,oldUsername)) {
            throw new NoPermissionException();
        }
        if (updateRequestDto.username() != null) {
            authorizationFacade.updateUsernameByOldUsername(oldUsername, updateRequestDto.username());
            updatedUserDetails.append("username");
        }
        if (updateRequestDto.email() != null) {
            authorizationFacade.updateEmailByUsername(oldUsername, updateRequestDto.email());
            if (updatedUserDetails.length()>0) {
                updatedUserDetails.append(", ");
            }
            updatedUserDetails.append("email");
        }
        if (updateRequestDto.password() != null) {
            String encodedPassword = bCryptPasswordEncoder.encode(updateRequestDto.password());
            authorizationFacade.updatePasswordByUsername(oldUsername, encodedPassword);
            if (updatedUserDetails.length()>0) {
                updatedUserDetails.append(", ");
            }
            updatedUserDetails.append("password");
        }
        emailSenderFacade.sendSecurityInformationEmail(
                authorizationFacade.findUserById(id),
                updatedUserDetails.toString());
    }
}
