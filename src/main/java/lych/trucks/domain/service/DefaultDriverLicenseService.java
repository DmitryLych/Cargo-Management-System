package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.DriverLicense;
import lych.trucks.domain.repository.DriverLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        validateDriverLicense(driverLicense);

        driverLicense.setDriverLicenseFk(driverId);

        final Driver driver = driverService.fetchDriver(driverId);

        driver.setDriverLicense(driverLicense);

        driverService.updateDriver(driver);

        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public DriverLicense fetchDriverLicense(final Integer driverId) {
        return Optional.ofNullable(driverLicenseRepository.findByDriverLicenseFk(driverId))
                .orElseThrow(() -> new IllegalArgumentException("Can`t find Driver License. Driver License with this"
                        + " driver Id: '" + driverId + "' not exist."));
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public DriverLicense updateDriverLicense(final DriverLicense driverLicense) {
        validateDriverLicense(driverLicense);

        final DriverLicense saved = Optional.ofNullable(driverLicenseRepository.findOne(driverLicense.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Driver License can`t find. Driver License with"
                        + " this Id: '" + driverLicense.getId() + "' not exist."));

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

        final List<DriverLicense> driverLicenses = driverLicenseRepository.findByCategory(category);

        if (driverLicenses == null || driverLicenses.isEmpty()) {
            throw new IllegalArgumentException("Driver Licenses can`t find. Driver Licenses with"
                    + " this category: '" + category + "' not exist.");
        }
        return driverLicenses;
    }

    @Override
    public List<DriverLicense> fetchDriverLicensesBySpecialNotes(final String specialNotes) {

        final List<DriverLicense> driverLicenses = driverLicenseRepository.findBySpecialNotes(specialNotes);

        if (driverLicenses == null || driverLicenses.isEmpty()) {
            throw new IllegalArgumentException("Driver Licenses can`t find. Driver Licenses with"
                    + "this special notes: '" + specialNotes + "' not exist.");
        }

        return driverLicenses;
    }

    private static void validateDriverLicense(final DriverLicense driverLicense) {

        if (driverLicense == null) {
            throw new IllegalArgumentException("Driver License can`t be null.");
        }
    }
}
