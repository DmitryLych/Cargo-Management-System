package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for {@link DriverLicense} work with database.
 */
public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Integer> {

    /**
     * Method for find driver license by driver Id.
     *
     * @param driverId {@link Driver} driverId.
     * @return driver license which found.
     */
    @Query(value = "SELECT * FROM driver_licenses WHERE driver_license_fk=?1", nativeQuery = true)
    DriverLicense findByDriverLicenseFk(Integer driverId);

    /**
     * Method for find driver licenses by category.
     *
     * @param category DriverLicense category.
     * @return list of found driver licenses.
     */
    @Query(value = "SELECT * FROM driver_licenses WHERE categories=?1", nativeQuery = true)
    List<DriverLicense> findByCategory(String category);

    /**
     * Method for find driver license by special notes.
     *
     * @param specialNotes DriverLicense specialNotes.
     * @return list of found driver license.
     */
    @Query(value = "SELECT * FROM driver_licenses WHERE special_notes=?1", nativeQuery = true)
    List<DriverLicense> findBySpecialNotes(String specialNotes);
}
