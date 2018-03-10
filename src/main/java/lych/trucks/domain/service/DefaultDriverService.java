package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.model.User;
import lych.trucks.application.security.service.UserService;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link DriverService}.
 */
@Service
@SuppressWarnings("PMD")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultDriverService implements DriverService {

    private final DriverRepository driverRepository;

    private final CompanyService companyService;

    private final UserService userService;

    @Override
    public List<Driver> fetchAllDrivers(final Integer companyId) {

        final List<Driver> drivers = driverRepository.findAllByCompany(companyId);

        if (drivers == null || drivers.isEmpty()) {
            throw new IllegalArgumentException("Drivers not found. Drivers in company "
                    + "with Id: '" + companyId + "' not exists.");
        }

        return drivers;
    }

    @Override
    public Driver createDriver(final Integer userId, final Integer companyId, final Driver driver) {
        validateDriver(driver);

        final Company company = companyService.fetchCompany(companyId);

        driver.setCompany(company);
        driver.setUser(userService.fetchUser(userId));

        return driverRepository.save(driver);
    }

    @Override
    public Driver fetchDriver(final Integer id) {
        return Optional.ofNullable(driverRepository.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException("Driver not found. Driver with"
                        + " this Id: '" + id + "' not exists."));
    }

    @Override
    public Driver deleteDriver(final Integer id) {

        final Driver driver = fetchDriver(id);

        driverRepository.delete(id);

        return driver;
    }

    @Override
    public Driver updateDriver(final Driver driver) {
        validateDriver(driver);

        final Driver saved = fetchDriver(driver.getId());

        driver.setCompany(driver.getCompany() == null ? saved.getCompany() : driver.getCompany());
        driver.setAddress(driver.getAddress() == null ? saved.getAddress() : driver.getAddress());
        driver.setDriverLicense(driver.getDriverLicense() == null ? saved.getDriverLicense()
                : driver.getDriverLicense());
        driver.setOrder(driver.getOrder() == null ? saved.getOrder() : driver.getOrder());
        driver.setFirstName(driver.getFirstName() == null ? saved.getFirstName() : driver.getFirstName());
        driver.setInsurancePolicies(driver.getInsurancePolicies() == null ? saved.getInsurancePolicies()
                : driver.getInsurancePolicies());
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
    public List<Driver> fetchDriversByLastNameAndFirstName(final String lastName, final String firstName) {

        final List<Driver> drivers = driverRepository.findByLastNameAndFirstName(lastName, firstName);

        if (drivers == null || drivers.isEmpty()) {
            throw new IllegalArgumentException("Drivers not found."
                    + " Drivers with this last name: '" + lastName + "' and first name: '" + firstName + "' not exists.");
        }
        return drivers;
    }

    @Override
    public List<Driver> fetchDriversByStatus(final boolean status) {

        final List<Driver> drivers = driverRepository.findByStatus(status);

        if (drivers == null || drivers.isEmpty()) {
            throw new IllegalArgumentException("Drivers not found. Drivers with status: '"
                    + status + "' not exists.");
        }

        return drivers;
    }

    @Override
    public boolean canCreate(final Integer userId, final Integer companyId) {
        companyService.canAccess(userId, companyId);

        return true;
    }

    @Override
    public boolean canAccess(final Integer userId, final Integer driverId) {
        final Driver driver = Optional.ofNullable(fetchDriver(driverId))
                .orElseThrow(() -> new AccessDeniedException("Access Denied!!!"));
        final Company company = driver.getCompany();
        final User user = userService.fetchUser(userId);

        if (!company.getUser().getId().equals(userId) && !driver.getUser().getId().equals(userId)
                && user.getAuthorities().stream()
                .noneMatch(authority -> "Admin".equalsIgnoreCase(authority.toString()))) {
            throw new AccessDeniedException("Access Denied!!!");
        }

        return true;
    }

    private static void validateDriver(final Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver can`t be null.");
        }
    }
}


