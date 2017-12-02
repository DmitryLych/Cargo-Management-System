package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.repository.DriverLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link DriverLicenseService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverLicenseService implements DriverLicenseService {

    private final DriverLicenseRepository driverLicenseRepository;

    private final DriverService driverService;

    @Override
    public DriverLicense create(final Integer driverId, final DriverLicense driverLicense) {

        log.info("Driver license created.");

        driverLicense.setDriverLicenseFk(driverId);

        final Driver driver = driverService.fetch(driverId);

        driver.setDriverLicense(driverLicense);

        driverService.update(driver);

        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public DriverLicense fetch(final Integer driverId) {

        log.info("Driver license displayed.");

        return driverLicenseRepository.findByOwnerIdForDriverLicense(driverId);
    }

    @Override
    public DriverLicense update(final DriverLicense driverLicense) {

        log.info("Driver license updated.");

        final DriverLicense saved = driverLicenseRepository.findOne(driverLicense.getId());

        driverLicense.setCategory(driverLicense.getCategory() == null ? saved.getCategory()
                : driverLicense.getCategory());
        driverLicense.setDriverLicenseFk(driverLicense.getDriverLicenseFk() == null ?
                saved.getDriverLicenseFk() : driverLicense.getDriverLicenseFk());
        driverLicense.setSpecialNotes(driverLicense.getSpecialNotes() == null ? saved.getSpecialNotes()
                : driverLicense.getSpecialNotes());
        driverLicense.setValidate(driverLicense.getValidate() == null ? saved.getValidate()
                : driverLicense.getValidate());

        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public List<DriverLicense> fetchByCategory(final String category) {

        log.info("Driver licenses found by category.");

        return driverLicenseRepository.findByCategory(category);
    }

    @Override
    public List<DriverLicense> fetchBySpecialNotes(final String specialNotes) {

        log.info("Driver licenses found by special notes.");

        return driverLicenseRepository.findBySpecialNotes(specialNotes);
    }
}
