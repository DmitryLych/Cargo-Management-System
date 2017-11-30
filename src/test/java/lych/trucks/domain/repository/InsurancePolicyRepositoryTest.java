package lych.trucks.domain.repository;

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

    private static final Integer DRIVER_ID_CONTENT = 1;

    private Date validateContent;

    private static final String TYPE_CONTENT = "type";

    @Before
    public void setUp() {

        final InsurancePolicy insurancePolicy = new InsurancePolicy();

        validateContent = new Date();

        insurancePolicy.setValidate(validateContent);
        insurancePolicy.setType(TYPE_CONTENT);
        insurancePolicy.setCost(DRIVER_ID_CONTENT);

        insurancePolicyRepository.save(insurancePolicy);
    }

    @Test
    public void findAllByOwnerIdForInsurancePolicy() {

        final List<InsurancePolicy> foundInsurancePolicies = insurancePolicyRepository.findAllByOwnerIdForInsurancePolicy(DRIVER_ID_CONTENT);

        foundInsurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getOwnerIdForInsurancePolicy(), Is.is(DRIVER_ID_CONTENT)));
    }

    @Test
    public void findByValidate() {

        final List<InsurancePolicy> foundInsurancePolicies = insurancePolicyRepository.findByValidate(validateContent);

        foundInsurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getValidate(), Is.is(validateContent)));
    }

    @Test
    public void findByType() {

        final List<InsurancePolicy> foundInsurancePolicies = insurancePolicyRepository.findByType(TYPE_CONTENT);

        foundInsurancePolicies.forEach(insurancePolicy -> assertThat(insurancePolicy.getType(), Is.is(TYPE_CONTENT)));
    }
}
