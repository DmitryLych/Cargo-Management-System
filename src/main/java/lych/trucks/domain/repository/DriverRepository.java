package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for work with database.
 */
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    /**
     * Method for fetch all drivers from the company.
     *
     * @param ownerId Company id.
     * @return List of Driver class objects.
     */
    @Query(value = "SELECT * FROM drivers WHERE owner_id=?1", nativeQuery = true)
    List<Driver> findAllByOwnerId(Integer ownerId);

    @Query(value = "SELECT * FROM drivers WHERE last_name=?1 AND first_name=?2", nativeQuery = true)
    List<Driver> findByLastNameAndFirstName(String lastName, String firstName);
}
