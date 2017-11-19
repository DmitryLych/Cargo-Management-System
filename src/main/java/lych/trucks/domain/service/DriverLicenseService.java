package lych.trucks.domain.service;

import lych.trucks.domain.model.DriverLicense;

/**
 * Service for {@link DriverLicense} with database.
 */
public interface DriverLicenseService {

    /**
     * Method for create Driver license.
     *
     * @param driverLicense DriverLicense driverLicense.
     * @return created DriverLicense driverLicense.
     */
    DriverLicense create(DriverLicense driverLicense);

    /**
     * Method for fetch Driver license by id.
     *
     * @param id DriverLicense id.
     * @return found DriverLicense response.
     */
    DriverLicense fetch(Integer id);

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
