package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;

import java.util.List;

/**
 * Service for {@link Driver} work with database.
 */
public interface DriverService {

    /**
     * Method for find all drivers.
     *
     * @param companyId Company companyId.
     * @return List of drivers.
     */
    List<Driver> fetchAllDrivers(Integer companyId);

    /**
     * Method for create driver.
     *
     * @param userId    a user id.
     * @param companyId Company companyId.
     * @param driver    Driver driver.
     * @return created driver.
     */
    Driver createDriver(Integer userId, Integer companyId, Driver driver);

    /**
     * Method for find some driver.
     *
     * @param id Driver id.
     * @return found driver.
     */
    Driver fetchDriver(Integer id);

    /**
     * Method for delete some driver.
     *
     * @param id Driver id.
     * @return deleted driver.
     */
    Driver deleteDriver(Integer id);

    /**
     * Method for update Driver.
     *
     * @param driver Driver driver.
     * @return updated driver.
     */
    Driver updateDriver(Driver driver);

    /**
     * Method for fetch drivers by last name and first name.
     *
     * @param lastName  Driver lastName.
     * @param firstName Driver firstName.
     * @return list of drivers which found.
     */
    List<Driver> fetchDriversByLastNameAndFirstName(String lastName, String firstName);

    /**
     * Method for fetch drivers by status.
     *
     * @param status Driver status.
     * @return list of drivers which found.
     */
    List<Driver> fetchDriversByStatus(boolean status);

    boolean canCreate(Integer userId, Integer companyId);

    boolean canAccess(Integer userId, Integer driverId);
}
