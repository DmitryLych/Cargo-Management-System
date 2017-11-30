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
     * @param ownerId Company ownerId.
     * @return List of drivers.
     */
    List<Driver> fetchAll(Integer ownerId);

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
     * Method for delete Driver.
     *
     * @param id Driver id.
     */
    void delete(Integer id);

    /**
     * Method for update Driver.
     *
     * @param driver Driver driver.
     * @return updated driver.
     */
    Driver update(Driver driver);

    List<Driver> fetchByLastNameAndFirstName(String lastName, String firstName);

    List<Driver> fetchByStatus(boolean status);
}
