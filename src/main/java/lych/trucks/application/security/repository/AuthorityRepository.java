package lych.trucks.application.security.repository;

import lych.trucks.application.security.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for {@link Authority}.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
