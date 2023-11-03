package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.dto.UpdateRequestDto;
import ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure.AuthorizationFacade;


@RestController
@AllArgsConstructor
@RequestMapping("/update")
public class UpdateController {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthorizationFacade authorizationFacade;
    @PatchMapping("/{oldUsername}")
    public void updateUsername(@PathVariable("oldUsername") String oldUsername, @RequestBody UpdateRequestDto updateRequestDto) {
        if (updateRequestDto.username() != null) {
            authorizationFacade.updateUsernameByOldUsername(oldUsername, updateRequestDto.username());
        }
        if (updateRequestDto.email() != null) {
            authorizationFacade.updateEmailByUsername(oldUsername, updateRequestDto.email());
        }
        if (updateRequestDto.password() != null) {
            String encodedPassword = bCryptPasswordEncoder.encode(updateRequestDto.password());
            authorizationFacade.updatePasswordByUsername(oldUsername, encodedPassword);
        }
    }
}
