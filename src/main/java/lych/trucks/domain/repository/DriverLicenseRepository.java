package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Integer> {

    /**
     * Method for find {@link DriverLicense} by driver Id.
     *
     * @param driverId {@link Driver} driverId.
     * @return driver license which found.
     */
    DriverLicense findByOwnerIdForDriverLicense(Integer driverId);
}
