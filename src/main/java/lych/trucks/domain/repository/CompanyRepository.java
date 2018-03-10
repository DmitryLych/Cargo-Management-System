package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for {@link Company} work with database.
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    /**
     * Find company by company name.
     *
     * @param companyName Company companyName.
     * @return found company.
     */
    Company findByCompanyName(String companyName);
}
