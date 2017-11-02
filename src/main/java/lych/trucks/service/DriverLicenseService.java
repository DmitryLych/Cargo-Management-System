package lych.trucks.service;

import lych.trucks.model.DriverLicense;

public interface DriverLicenseService {

    DriverLicense create(DriverLicense driverLicense);

    DriverLicense fetch(Integer id);

    void delete(Integer id);

    DriverLicense update(DriverLicense driverLicense);
}
