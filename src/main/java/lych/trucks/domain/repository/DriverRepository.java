package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

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
    List<Driver> findAllByOwnerId(Integer ownerId);
}
