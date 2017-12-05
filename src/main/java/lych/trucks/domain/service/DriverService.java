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
    List<Driver> fetchAll(Integer companyId);

    /**
     * Method for create driver.
     *
     * @param companyId Company companyId.
     * @param driver    Driver driver.
     * @return created driver.
     */
    Driver create(Integer companyId, Driver driver);

    /**
     * Method for find some driver.
     *
     * @param id Driver id.
     * @return found driver.
     */
    Driver fetch(Integer id);

    /**
     * Method for delete some driver.
     *
     * @param id Driver id.
     * @return deleted driver.
     */
    Driver delete(Integer id);

    /**
     * Method for update Driver.
     *
     * @param driver Driver driver.
     * @return updated driver.
     */
    Driver update(Driver driver);

    /**
     * Method for fetch drivers by last name and first name.
     *
     * @param lastName  Driver lastName.
     * @param firstName Driver firstName.
     * @return list of drivers which found.
     */
    List<Driver> fetchByLastNameAndFirstName(String lastName, String firstName);

    /**
     * Method for fetch drivers by status.
     *
     * @param status Driver status.
     * @return list of drivers which found.
     */
    List<Driver> fetchByStatus(boolean status);
}
