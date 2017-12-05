package lych.trucks.domain.service;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.InsurancePolicyRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultInsurancePolicyServiceTest {

    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    private DriverRepository driverRepository;

    private Integer driverIdContent;

    private Integer insurancePolicyIdContent;

    private static final Date VALIDATE_CONTENT = new Date();

    private static final String TYPE_CONTENT = "content";

    @Before
    public void setUp() {

        driverIdContent = driverRepository.save(new Driver()).getId();

        final InsurancePolicy insurancePolicy = new InsurancePolicy();

        insurancePolicy.setInsurancePolicyFk(driverIdContent);
        insurancePolicy.setType(TYPE_CONTENT);
        insurancePolicy.setValidate(VALIDATE_CONTENT);

        insurancePolicyIdContent = insurancePolicyRepository.save(insurancePolicy).getId();
    }

    @Test
    public void fetchAll() {

        final List<InsurancePolicy> insurancePolicies = insurancePolicyService.fetchAll(driverIdContent);

        insurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getType(), Is.is(TYPE_CONTENT)));
    }

    @Test
    public void create() {

        final InsurancePolicy insurancePolicy = new InsurancePolicy();

        final String content = "new content";

        insurancePolicy.setType(content);

        final Integer newId = insurancePolicyService.create(driverIdContent, insurancePolicy).getId();

        assertThat(insurancePolicyRepository.findOne(newId).getType(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(insurancePolicyService.fetch(insurancePolicyIdContent).getType(), Is.is(TYPE_CONTENT));
    }

    @Test
    public void delete() {

        insurancePolicyService.delete(insurancePolicyIdContent);

        assertThat(insurancePolicyRepository.exists(insurancePolicyIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findOne(insurancePolicyIdContent);

        final String content = "new content";

        insurancePolicy.setType(content);

        insurancePolicyService.update(insurancePolicy);

        assertThat(insurancePolicyRepository.findOne(insurancePolicyIdContent).getType(), Is.is(content));
    }

    @Test
    public void fetchByValidate() {

        final List<InsurancePolicy> insurancePolicies = insurancePolicyService.fetchByValidate(VALIDATE_CONTENT);

        insurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getValidate().getTime(),
                Is.is(VALIDATE_CONTENT.getTime())));
    }

    @Test
    public void findByType() {

        final List<InsurancePolicy> insurancePolicies = insurancePolicyService.findByType(TYPE_CONTENT);

        insurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getType(), Is.is(TYPE_CONTENT)));
    }
}
