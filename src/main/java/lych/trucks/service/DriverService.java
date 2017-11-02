package lych.trucks.service;

import lych.trucks.model.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> drivers();

    Driver create(Driver driver);

    Driver fetch(Integer id);

    void delete(Integer id);

    Driver update(Driver driver);
}
