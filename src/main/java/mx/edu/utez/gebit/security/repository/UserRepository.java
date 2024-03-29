package mx.edu.utez.gebit.security.repository;

import mx.edu.utez.gebit.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM User u WHERE u.username = ?1", nativeQuery = true)
    User findByEmail(String email);
    User findByResetPasswordToken(String token);

}
