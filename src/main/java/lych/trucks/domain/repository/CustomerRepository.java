package lych.trucks.domain.repository;

import lych.trucks.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for {@link Customer} work with database.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Method for find by company name.
     *
     * @param companyName Customer companyName.
     * @return customer which found.
     */
    @Query(value = "SELECT * FROM customers WHERE company_name=?1", nativeQuery = true)
    Customer findByCompanyName(String companyName);

    /**
     * Method for find by last name.
     *
     * @param lastName Customer lastName.
     * @return customer which found.
     */
    @Query(value = "SELECT * FROM customers WHERE last_name=?1", nativeQuery = true)
    Customer findByLastName(String lastName);
}
