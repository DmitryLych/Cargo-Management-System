package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@SuppressWarnings("PMD")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultInsurancePolicyService implements InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    private final DriverService driverService;

    @Override
    public List<InsurancePolicy> fetchAll(final Integer driverId) {

        log.info("Insurance policies displayed.");

        return insurancePolicyRepository.findAllByDriver(driverId);
    }

    @Override
    public InsurancePolicy create(final Integer driverId, final InsurancePolicy insurancePolicy) {

        log.info("Insurance policy updated.");

        final Driver driver = driverService.fetch(driverId);

        insurancePolicy.setDriver(driver);

        return insurancePolicyRepository.save(insurancePolicy);
    }

    @Override
    public InsurancePolicy fetch(final Integer id) {

        log.info("Insurance policy displayed.");

        return insurancePolicyRepository.findOne(id);
    }

    @Override
    public InsurancePolicy delete(final Integer id) {

        log.info("Insurance policy deleted.");

        final InsurancePolicy insurancePolicy = insurancePolicyRepository.findOne(id);

        insurancePolicyRepository.delete(id);

        return insurancePolicy;
    }

    @Override
    public InsurancePolicy update(final InsurancePolicy insurancePolicy) {

        log.info("Insurance policy updated.");

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
    public List<InsurancePolicy> fetchByValidate(final Long validate) {

        log.info("Insurance policy fetched by validate.");

        final Date date = new Date();

        date.setTime(validate);

        return insurancePolicyRepository.findByValidate(date);
    }

    @Override
    public List<InsurancePolicy> fetchByType(final String type) {

        log.info("Insurance policy fetched by type.");

        return insurancePolicyRepository.findByType(type);
    }
}
