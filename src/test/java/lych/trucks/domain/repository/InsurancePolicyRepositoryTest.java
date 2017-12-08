package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InsurancePolicyRepositoryTest {

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private DriverRepository driverRepository;

    private Integer driverIdContent;

    private static final Date VALIDATE_CONTENT = new Date();

    private static final String TYPE_CONTENT = "type";

    @Before
    public void setUp() {

        final InsurancePolicy insurancePolicy = new InsurancePolicy();

        final Driver driver = driverRepository.save(new Driver());

        driverIdContent = driver.getId();

        insurancePolicy.setValidate(VALIDATE_CONTENT);
        insurancePolicy.setType(TYPE_CONTENT);
        insurancePolicy.setDriver(driver);

        insurancePolicyRepository.save(insurancePolicy);
    }

    @Test
    public void findAllByDriver() {

        final List<InsurancePolicy> foundInsurancePolicies = insurancePolicyRepository.
                findAllByDriver(driverIdContent);

        foundInsurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy
                .getDriver().getId(), Is.is(driverIdContent)));
    }

    @Test
    public void findByValidate() {

        final List<InsurancePolicy> foundInsurancePolicies = insurancePolicyRepository
                .findByValidate(VALIDATE_CONTENT);

        foundInsurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getValidate(),
                Is.is(VALIDATE_CONTENT)));
    }

    @Test
    public void findByType() {

        final List<InsurancePolicy> foundInsurancePolicies = insurancePolicyRepository.findByType(TYPE_CONTENT);

        foundInsurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getType(), Is.is(TYPE_CONTENT)));
    }
}
