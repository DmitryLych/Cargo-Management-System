package lych.trucks.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.model.DriverLicense;
import lych.trucks.repository.DriverLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverLicenseService implements DriverLicenseService{

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
