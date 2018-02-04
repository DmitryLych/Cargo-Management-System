package lych.trucks.domain.service;

import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.repository.CompanyRepository;
import lych.trucks.domain.repository.DriverRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DefaultDriverServiceTest {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Integer companyIdContent;

    private Integer driverIdContent;

    private static final String FIRST_NAME_CONTENT = "firstName";

    private static final String LAST_NAME_CONTENT = "lastName";

    private static final boolean STATUS_CONTENT = true;

    @Before
    public void setUp() {

        final Company company = companyRepository.save(new Company());

        companyIdContent = company.getId();

        final Driver driver = new Driver();

        driver.setStatus(STATUS_CONTENT);
        driver.setFirstName(FIRST_NAME_CONTENT);
        driver.setLastName(LAST_NAME_CONTENT);
        driver.setCompany(company);

        driverIdContent = driverRepository.save(driver).getId();

    }

    @Test
    public void fetchAll() {

        final List<Driver> drivers = driverService.fetchAllDrivers(companyIdContent);

        drivers.forEach(driver -> assertThat(driver.getCompany().getId(), Is.is(companyIdContent)));
    }

    @Test
    public void create() {

        final String content = "new";

        final Driver driver = new Driver();

        driver.setLastName(content);

        final Integer newId = driverService.createDriver(companyIdContent, driver).getId();

        assertThat(driverRepository.findOne(newId).getLastName(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(driverService.fetchDriver(driverIdContent).getLastName(), Is.is(LAST_NAME_CONTENT));
    }

    @Test
    public void delete() {

        driverService.deleteDriver(driverIdContent);

        assertThat(driverRepository.exists(driverIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final Driver driver = driverRepository.findOne(driverIdContent);

        final String content = "updated";

        driver.setLastName(content);

        driverService.updateDriver(driver);

        assertThat(driverRepository.findOne(driverIdContent).getLastName(), Is.is(content));
    }

    @Test
    public void fetchByLastNameAndFirstName() {

        final List<Driver> drivers = driverService.fetchDriversByLastNameAndFirstName(LAST_NAME_CONTENT, FIRST_NAME_CONTENT);

        drivers.forEach(driver -> assertThat(driver.getLastName(), Is.is(LAST_NAME_CONTENT)));
    }

    @Test
    public void fetchByStatus() {

        final List<Driver> drivers = driverService.fetchDriversByStatus(STATUS_CONTENT);

        drivers.forEach(driver -> assertThat(driver.isStatus(), Is.is(true)));
    }

}
