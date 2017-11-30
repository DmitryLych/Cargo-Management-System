package lych.trucks.domain.service;

import lych.trucks.domain.model.DriverLicense;

import java.util.List;

/**
 * Service for {@link DriverLicense} work with database.
 */
public interface DriverLicenseService {

    /**
     * Method for create Driver license.
     *
     * @param driverId      Driver driverId.
     * @param driverLicense DriverLicense driverLicense.
     * @return created DriverLicense driverLicense.
     */
    DriverLicense create(Integer driverId, DriverLicense driverLicense);

    /**
     * Method for fetch Driver license by id.
     *
     * @param driverId Driver driverId.
     * @return found DriverLicense response.
     */
    DriverLicense fetch(Integer driverId);

    /**
     * Method for delete some driver license.
     *
     * @param id DriverLicense id.
     * @return deleted driver license.
     */
    DriverLicense delete(Integer id);

    /**
     * Method for update Driver license.
     *
     * @param driverLicense DriverLicense driverLicense.
     * @return updated DriverLicense driverLicense.
     */
    DriverLicense update(DriverLicense driverLicense);

    /**
     * Method for fetch driver licenses by category.
     *
     * @param category DriverLicense category.
     * @return list of driver licenses which found.
     */
    List<DriverLicense> fetchByCategory(String category);

    /**
     * Method for fetch driver license by special notes.
     *
     * @param specialNotes DriverLicense specialNotes.
     * @return list of driver license which found.
     */
    List<DriverLicense> fetchBySpecialNotes(String specialNotes);
}
