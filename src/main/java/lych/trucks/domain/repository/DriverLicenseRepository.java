package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

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
    @Query(value = "SELECT * FROM driver_licenses WHERE owner_id_for_driver_license=?1", nativeQuery = true)
    DriverLicense findByOwnerIdForDriverLicense(Integer driverId);

    @Query(value = "SELECT * FROM driver_licenses WHERE categories=?1", nativeQuery = true)
    DriverLicense findByCategory(String category);

    @Query(value = "SELECT * FROM driver_licenses WHERE special_notes=?1", nativeQuery = true)
    DriverLicense findBySpecialNotes(String specialNotes);

    @Query(value = "SELECT * FROM driver_licenses WHERE validate=?1", nativeQuery = true)
    DriverLicense findByValidate(Date validate);
}
