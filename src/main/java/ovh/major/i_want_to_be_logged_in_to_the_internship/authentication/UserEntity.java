package ovh.major.i_want_to_be_logged_in_to_the_internship.authentication;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
 }
