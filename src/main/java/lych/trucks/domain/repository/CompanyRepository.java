package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for work with database.
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query(value = "SELECT * FROM companies WHERE company_name=?1", nativeQuery = true)
    Company findByCompanyName(String companyName);
}
