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
    public DriverLicense create(final Integer driverId, final DriverLicense driverLicense) {

        driverLicense.setOwnerIdForDriverLicense(driverId);

        return driverLicenseRepository.save(driverLicense);
    }

    @Override
    public DriverLicense fetch(final Integer driverId) {
        return driverLicenseRepository.findByOwnerIdForDriverLicense(driverId);
    }

    @Override
    public void delete(final Integer id) {
        driverLicenseRepository.delete(id);
    }

    @Override
    public DriverLicense update(final DriverLicense driverLicense) {

        final DriverLicense saved = driverLicenseRepository.findOne(driverLicense.getId());

        driverLicense.setOwnerIdForDriverLicense(saved.getOwnerIdForDriverLicense());
        driverLicense.setCategory(driverLicense.getCategory() == null ? saved.getCategory() : driverLicense.getCategory());
        driverLicense.setSpecialNotes(driverLicense.getSpecialNotes() == null ? saved.getSpecialNotes() : driverLicense.getSpecialNotes());
        driverLicense.setValidate(driverLicense.getValidate() == null ? saved.getValidate() : driverLicense.getValidate());

        return driverLicenseRepository.save(driverLicense);
    }
}
