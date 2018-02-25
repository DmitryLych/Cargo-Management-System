package lych.trucks.application.security.repository;

import lych.trucks.application.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
