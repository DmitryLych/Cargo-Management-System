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

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultDriverServiceTest {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private static final String LAST_NAME_CONTENT = "last name";

    private static final String FIRST_NAME_CONTENT = "first name";

    private static final boolean STATUS_CONTENT = true;

    private Integer companyIdContent;

    private Integer driverIdContent;

    @Before
    public void setUp() {

        companyIdContent = companyRepository.save(new Company()).getId();

        final Driver driver = new Driver();

        driver.setOwnerId(companyIdContent);
        driver.setFirstName(FIRST_NAME_CONTENT);
        driver.setLastName(LAST_NAME_CONTENT);
        driver.setStatus(STATUS_CONTENT);

        driverIdContent = driverRepository.save(driver).getId();
    }

    @Test
    public void create() {

        final String content = "newName";

        final Driver driver = new Driver();

        driver.setLastName(content);

        final Integer newId = driverService.create(companyIdContent, driver).getId();

        assertThat(driverRepository.findOne(newId).getLastName(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(driverService.fetch(driverIdContent).getLastName(), Is.is(LAST_NAME_CONTENT));
    }

    @Test
    public void delete() {

        driverService.delete(driverIdContent);

        assertThat(driverRepository.exists(driverIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final String content = "new first name";

        final Driver driver = driverRepository.findOne(driverIdContent);

        driver.setFirstName(content);

        driverService.update(driver);

        assertThat(driverRepository.findOne(driverIdContent).getFirstName(), Is.is(content));
    }

    @Test
    public void fetchByLastNameAndFirstName() {

        final List<Driver> drivers = driverService.fetchByLastNameAndFirstName(LAST_NAME_CONTENT, FIRST_NAME_CONTENT);

        drivers.forEach(driver -> assertThat(driver.isStatus(), Is.is(true)));
    }

    @Test
    public void fetchByStatus() {

        final List<Driver> drivers = driverService.fetchByStatus(STATUS_CONTENT);

        drivers.forEach(driver -> assertThat(driver.getLastName(), Is.is(LAST_NAME_CONTENT)));
    }

}
