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
     * @param customerName Customer customerName.
     * @return customer which found.
     */
    @Query(value = "SELECT * FROM customers WHERE company_name=?1", nativeQuery = true)
    Customer findByCustomerName(String customerName);
}
