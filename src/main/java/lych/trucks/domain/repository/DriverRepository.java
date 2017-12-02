package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Interface for {@link Driver} work with database.
 */
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    /**
     * Method for fetch all drivers from the company.
     *
     * @param companyId {@link Company} companyId.
     * @return List of Driver class objects.
     */
    @Query(value = "SELECT * FROM drivers WHERE company_id=?1", nativeQuery = true)
    List<Driver> findAllByCompany(Integer companyId);

    /**
     * Method for find drivers by last name and first name.
     *
     * @param lastName  Driver lastName.
     * @param firstName Driver firstName.
     * @return list of found drivers.
     */
    @Query(value = "SELECT * FROM drivers WHERE last_name=?1 AND first_name=?2", nativeQuery = true)
    List<Driver> findByLastNameAndFirstName(String lastName, String firstName);

    /**
     * Method for find drivers by status.
     *
     * @param status Driver status.
     * @return list of found drivers.
     */
    @Query(value = "SELECT * FROM drivers WHERE status=?1", nativeQuery = true)
    List<Driver> findByStatus(boolean status);
}
