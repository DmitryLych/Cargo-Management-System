package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
