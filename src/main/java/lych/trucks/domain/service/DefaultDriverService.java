package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverService implements DriverService {

    private final DriverRepository driverRepository;


    @Override
    public List<Driver> fetchAll(Integer ownerId) {
        return driverRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public Driver create(Integer companyId, Driver driver) {

        log.info("Driver was added.");

        driver.setOwnerId(companyId);

        return driverRepository.save(driver);
    }

    @Override
    public Driver fetch(Integer id) {
        return driverRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        driverRepository.delete(id);
    }

    @Override
    public Driver update(Driver driver) {

        final Driver saved = driverRepository.findOne(driver.getId());

        driver.setOwnerId(driver.getOwnerId() == null ? saved.getOwnerId() : driver.getOwnerId());
        driver.setAddress(driver.getAddress() == null ? saved.getAddress() : driver.getAddress());
        driver.setDriverLicense(driver.getDriverLicense() == null ? saved.getDriverLicense() : driver.getDriverLicense());
        driver.setFirstName(driver.getFirstName() == null ? saved.getFirstName() : driver.getFirstName());
        driver.setInsurancePolicy(driver.getInsurancePolicy() == null ? saved.getInsurancePolicy() : driver.getInsurancePolicy());
        driver.setLastName(driver.getLastName() == null ? saved.getLastName() : driver.getLastName());
        driver.setMedicalExamination(driver.getMedicalExamination() == null ? saved.getMedicalExamination() : driver.getMedicalExamination());
        driver.setTruck(driver.getTruck() == null ? saved.getTruck() : driver.getTruck());
        driver.setYearOfIssue(driver.getYearOfIssue() == null ? saved.getYearOfIssue() : driver.getYearOfIssue());

        return driverRepository.save(driver);
    }
}
