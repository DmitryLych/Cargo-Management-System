package lych.trucks.domain.service;

import lych.trucks.domain.model.DriverLicense;

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
     * Method for delete Driver license by id.
     *
     * @param id DriverLicense id.
     */
    void delete(Integer id);

    /**
     * Method for update Driver license.
     *
     * @param driverLicense DriverLicense driverLicense.
     * @return updated DriverLicense driverLicense.
     */
    DriverLicense update(DriverLicense driverLicense);
}
