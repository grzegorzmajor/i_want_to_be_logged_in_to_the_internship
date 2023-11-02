package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.authentication.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying
    UserEntity save(UserEntity userEntity);

    @Query("SELECT user FROM UserEntity user WHERE user.username = :username")
    UserEntity findByUsername(String username);

    @Modifying
    @Query("DELETE FROM UserEntity user WHERE user.username = :username")
    void deleteUserByUsername(String username);

    @Query("SELECT user.id FROM UserEntity user WHERE user.username = :username")
    Integer findUserIdByUsername(String username);

    @Modifying
    @Query("UPDATE UserEntity user SET user.username = :username WHERE user.id = :id")
    void updateUsernameById(Integer id, String username);

    @Modifying
    @Query("UPDATE UserEntity user SET user.email = :email, user.emailAuthenticated = false WHERE user.username = :username")
    void updateEmailByUsername(String username, String email);

    @Modifying
    @Query("UPDATE UserEntity user SET user.password = :password WHERE user.username = :username")
    void updatePasswordByUsername(String username, String password);

    @Modifying
    @Query("UPDATE UserEntity user SET user.emailAuthenticated = true WHERE user.username = :username")
    void emailAuthenticateByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(user) > 0 THEN true ELSE false END FROM UserEntity user WHERE user.username = :username OR user.email = :email")
    boolean existsUserEntityByUsernameOrEmail(String username, String email);

}
