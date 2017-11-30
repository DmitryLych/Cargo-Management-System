package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for {@link Company} work with database.
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    /**
     * Method for find company by company name.
     *
     * @param companyName Company companyName.
     * @return found company.
     */
    @Query(value = "SELECT * FROM companies WHERE company_name=?1", nativeQuery = true)
    Company findByCompanyName(String companyName);
}
