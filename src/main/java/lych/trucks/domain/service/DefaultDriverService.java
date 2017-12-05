package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link DriverService}.
 */
@Service
@Slf4j
@SuppressWarnings("PMD")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverService implements DriverService {

    private final DriverRepository driverRepository;

    private final CompanyService companyService;

    @Override
    public List<Driver> fetchAll(final Integer companyId) {

        log.info("Drivers displayed.");

        return driverRepository.findAllByCompany(companyId);
    }

    @Override
    public Driver create(final Integer companyId, final Driver driver) {

        log.info("Driver added.");

        final Company company = companyService.fetch(companyId);

        driver.setCompany(company);

        return driverRepository.save(driver);
    }

    @Override
    public Driver fetch(final Integer id) {

        log.info("Driver displayed.");

        return driverRepository.findOne(id);
    }

    @Override
    public Driver delete(final Integer id) {

        log.info("Driver deleted.");

        final Driver driver = driverRepository.findOne(id);

        driverRepository.delete(id);

        return driver;
    }

    @Override
    public Driver update(final Driver driver) {

        log.info("Driver updated.");

        final Driver saved = driverRepository.findOne(driver.getId());

        driver.setCompany(driver.getCompany() == null ? saved.getCompany() : driver.getCompany());
        driver.setAddress(driver.getAddress() == null ? saved.getAddress() : driver.getAddress());
        driver.setDriverLicense(driver.getDriverLicense() == null ? saved.getDriverLicense()
                : driver.getDriverLicense());
        driver.setOrder(driver.getOrder() == null ? saved.getOrder() : driver.getOrder());
        driver.setFirstName(driver.getFirstName() == null ? saved.getFirstName() : driver.getFirstName());
        driver.setInsurancePolicy(driver.getInsurancePolicy() == null ? saved.getInsurancePolicy()
                : driver.getInsurancePolicy());
        driver.setTelephoneNumber(driver.getTelephoneNumber() == null ? saved.getTelephoneNumber()
                : driver.getTelephoneNumber());
        driver.setEmail(driver.getEmail() == null ? saved.getEmail() : driver.getEmail());
        driver.setStatus(driver.isStatus() ? driver.isStatus() : saved.isStatus());
        driver.setLastName(driver.getLastName() == null ? saved.getLastName() : driver.getLastName());
        driver.setMedicalExamination(driver.getMedicalExamination() == null ? saved.getMedicalExamination()
                : driver.getMedicalExamination());
        driver.setTruck(driver.getTruck() == null ? saved.getTruck() : driver.getTruck());
        driver.setYearOfIssue(saved.getYearOfIssue());

        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> fetchByLastNameAndFirstName(final String lastName, final String firstName) {

        log.info("Drivers fetch by last name and first name.");

        return driverRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    @Override
    public List<Driver> fetchByStatus(final boolean status) {

        log.info("Drivers fetch by status.");

        return driverRepository.findByStatus(status);
    }
}
