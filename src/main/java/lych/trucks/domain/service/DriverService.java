package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> fetchAll(Integer ownerId);

    Driver create(Integer companyId, Driver driver);

    Driver fetch(Integer id);

    void delete(Integer id);

    Driver update(Driver driver);
}
