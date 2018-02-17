package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import lych.trucks.domain.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        final List<InsurancePolicy> insurancePolicies = insurancePolicyRepository.findAllByDriver(driverId);

        if (insurancePolicies == null || insurancePolicies.isEmpty()) {
            throw new IllegalArgumentException("Insurance policies not found. Driver with Id: '" + driverId
                    + "' don`t have insurance policies.");
        }

        return insurancePolicies;
    }

    @Override
    public InsurancePolicy createInsurancePolicy(final Integer driverId, final InsurancePolicy insurancePolicy) {
        validateInsurancePolicy(insurancePolicy);

        final Driver driver = driverService.fetchDriver(driverId);

        insurancePolicy.setDriver(driver);

        return insurancePolicyRepository.save(insurancePolicy);
    }

    @Override
    public InsurancePolicy fetchInsurancePolicy(final Integer id) {

        return Optional.ofNullable(insurancePolicyRepository.findOne(id))
                .orElseThrow(() -> new IllegalArgumentException("Insurance policy not found. "
                        + "Insurance policy with this Id: '" + id + "' not exists."));
    }

    @Override
    public InsurancePolicy deleteInsurancePolicy(final Integer id) {

        final InsurancePolicy insurancePolicy = fetchInsurancePolicy(id);

        insurancePolicyRepository.delete(id);

        return insurancePolicy;
    }

    @Override
    public InsurancePolicy updateInsurancePolicy(final InsurancePolicy insurancePolicy) {
        validateInsurancePolicy(insurancePolicy);

        final InsurancePolicy saved = fetchInsurancePolicy(insurancePolicy.getId());

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

        final Date validateTime = new Date();

        validateTime.setTime(Optional.of(validate)
                .orElseThrow(() -> new IllegalArgumentException("Date can`t be null.")));

        final List<InsurancePolicy> insurancePolicies = insurancePolicyRepository.findByValidate(validateTime);

        if (insurancePolicies == null || insurancePolicies.isEmpty()) {
            throw new IllegalArgumentException("Insurance policies not found. "
                    + "Insurance policies with this validate time: '" + validateTime + "' not exists.");
        }

        return insurancePolicies;
    }

    @Override
    public List<InsurancePolicy> fetchInsurancePoliciesByType(final String type) {

        final List<InsurancePolicy> insurancePolicies = insurancePolicyRepository.findByType(type);

        if (insurancePolicies == null || insurancePolicies.isEmpty()) {
            throw new IllegalArgumentException("Insurance policies not found. "
                    + "Insurance policies with this type: '" + type + "'not exists.");
        }

        return insurancePolicies;
    }

    private static void validateInsurancePolicy(final InsurancePolicy insurancePolicy) {
        if (insurancePolicy == null) {
            throw new IllegalArgumentException("Insurance policy can`t be null.");
        }
    }
}
