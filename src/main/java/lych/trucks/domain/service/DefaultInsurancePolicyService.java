package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import lych.trucks.domain.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link InsurancePolicyService}.
 */
@Service
@SuppressWarnings("PMD")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultInsurancePolicyService implements InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    private final DriverService driverService;

    @Override
    public List<InsurancePolicy> fetchAllInsurancePolicies(final Integer driverId) {
        return insurancePolicyRepository.findAllByDriver(driverId);
    }

    @Override
    public InsurancePolicy createInsurancePolicy(final Integer driverId, final InsurancePolicy insurancePolicy) {

        final Driver driver = driverService.fetchDriver(driverId);

        insurancePolicy.setDriver(driver);

        return insurancePolicyRepository.save(insurancePolicy);
    }

    @Override
    public InsurancePolicy fetchInsurancePolicy(final Integer id) {
        return insurancePolicyRepository.findOne(id);
    }

    @Override
    public InsurancePolicy deleteInsurancePolicy(final Integer id) {

        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findOne(id);

        insurancePolicyRepository.delete(id);

        return insurancePolicy;
    }

    @Override
    public InsurancePolicy updateInsurancePolicy(final InsurancePolicy insurancePolicy) {

        final InsurancePolicy saved = insurancePolicyRepository.findOne(insurancePolicy.getId());

        insurancePolicy.setDriver(insurancePolicy.getDriver() == null ? saved.getDriver()
                : insurancePolicy.getDriver());
        insurancePolicy.setCost(insurancePolicy.getCost() == null ? saved.getCost() : insurancePolicy.getCost());
        insurancePolicy.setType(insurancePolicy.getType() == null ? saved.getType() : insurancePolicy.getType());
        insurancePolicy.setValidate(insurancePolicy.getValidate().getTime() == 0 ? saved.getValidate()
                : insurancePolicy.getValidate());

        return insurancePolicyRepository.save(insurancePolicy);
    }

    @Override
    public List<InsurancePolicy> fetchInsurancePoliciesByValidate(final Long validate) {

        final Date date = new Date();

        date.setTime(validate);

        return insurancePolicyRepository.findByValidate(date);
    }

    @Override
    public List<InsurancePolicy> fetchInsurancePoliciesByType(final String type) {
        return insurancePolicyRepository.findByType(type);
    }
}
