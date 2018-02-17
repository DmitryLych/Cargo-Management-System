package lych.trucks.domain.repository;

import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Driver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DriverRepositoryTest {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Integer companyIdContent;

    private static final String LAST_NAME_CONTENT = "lastName";

    private static final String FIRST_NAME_CONTENT = "fistName";

    private static final boolean STATUS_CONTENT = true;

    @Before
    public void setUp() {

        final Company company = companyRepository.save(new Company());

        companyIdContent = company.getId();

        final Driver driver = new Driver();

        driver.setStatus(STATUS_CONTENT);
        driver.setLastName(LAST_NAME_CONTENT);
        driver.setFirstName(FIRST_NAME_CONTENT);
        driver.setCompany(company);

        driverRepository.save(driver);
    }

    @Test
    public void findAllByCompany() {

        final List<Driver> foundDrivers = driverRepository.findAllByCompany(companyIdContent);

        foundDrivers.forEach(driver -> assertThat(driver.getCompany().getId(), is(companyIdContent)));
    }

    @Test
    public void findByLastNameAndFirstName() {

        final List<Driver> foundDrivers = driverRepository
                .findByLastNameAndFirstName(LAST_NAME_CONTENT, FIRST_NAME_CONTENT);

        foundDrivers.forEach(driver -> assertThat(driver.getLastName(), is(LAST_NAME_CONTENT)));
    }

    @Test
    public void findByStatus() {

        final List<Driver> foundDrivers = driverRepository.findByStatus(STATUS_CONTENT);

        foundDrivers.forEach(driver -> assertThat(driver.isStatus(), is(STATUS_CONTENT)));
    }
}
