package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.repository.DriverLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link DriverLicenseService}
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverLicenseService implements DriverLicenseService {

    private final DriverLicenseRepository driverLicenseRepository;

    @Override
    public DriverLicense create(DriverLicense driverLicense) {
        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public DriverLicense fetch(Integer id) {
        return driverLicenseRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        driverLicenseRepository.delete(id);
    }

    @Override
    public DriverLicense update(DriverLicense driverLicense) {
        return driverLicenseRepository.save(driverLicense);
    }
}
