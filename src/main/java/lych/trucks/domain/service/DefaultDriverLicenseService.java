package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.repository.DriverLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link DriverLicenseService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverLicenseService implements DriverLicenseService {

    private final DriverLicenseRepository driverLicenseRepository;

    private final DriverService driverService;

    @Override
    public DriverLicense createDriverLicense(final Integer driverId, final DriverLicense driverLicense) {

        driverLicense.setDriverLicenseFk(driverId);

        final Driver driver = driverService.fetchDriver(driverId);

        driver.setDriverLicense(driverLicense);

        driverService.updateDriver(driver);

        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public DriverLicense fetchDriverLicense(final Integer driverId) {
        return driverLicenseRepository.findByDriverLicenseFk(driverId);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public DriverLicense updateDriverLicense(final DriverLicense driverLicense) {

        final DriverLicense saved = driverLicenseRepository.findOne(driverLicense.getId());

        driverLicense.setCategory(driverLicense.getCategory() == null ? saved.getCategory()
                : driverLicense.getCategory());
        driverLicense.setDriverLicenseFk(driverLicense.getDriverLicenseFk() == null
                ? saved.getDriverLicenseFk() : driverLicense.getDriverLicenseFk());
        driverLicense.setSpecialNotes(driverLicense.getSpecialNotes() == null ? saved.getSpecialNotes()
                : driverLicense.getSpecialNotes());
        driverLicense.setValidate(driverLicense.getValidate() == null ? saved.getValidate()
                : driverLicense.getValidate());

        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public List<DriverLicense> fetchDriverLicensesByCategory(final String category) {
        return driverLicenseRepository.findByCategory(category);
    }

    @Override
    public List<DriverLicense> fetchDriverLicensesBySpecialNotes(final String specialNotes) {
        return driverLicenseRepository.findBySpecialNotes(specialNotes);
    }
}
