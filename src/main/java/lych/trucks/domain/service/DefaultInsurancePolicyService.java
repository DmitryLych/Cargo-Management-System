package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.InsurancePolicy;
import lych.trucks.domain.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link InsurancePolicyService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultInsurancePolicyService implements InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public List<InsurancePolicy> fetchAll(final Integer driverId) {

        return insurancePolicyRepository.findAllByOwnerIdForInsurancePolicy(driverId);
    }

    @Override
    public InsurancePolicy create(final Integer driverId, final InsurancePolicy insurancePolicy) {

        insurancePolicy.setOwnerIdForInsurancePolicy(driverId);

        return insurancePolicyRepository.save(insurancePolicy);
    }

    @Override
    public InsurancePolicy fetch(final Integer id) {
        return insurancePolicyRepository.findOne(id);
    }

    @Override
    public void delete(final Integer id) {

        insurancePolicyRepository.delete(id);
    }

    @Override
    public InsurancePolicy update(final InsurancePolicy insurancePolicy) {

        final InsurancePolicy saved = insurancePolicyRepository.findOne(insurancePolicy.getId());

        insurancePolicy.setOwnerIdForInsurancePolicy(insurancePolicy.getOwnerIdForInsurancePolicy() == null
                ? saved.getOwnerIdForInsurancePolicy() : insurancePolicy.getOwnerIdForInsurancePolicy());
        insurancePolicy.setCost(insurancePolicy.getCost() == 0 ? saved.getCost() : insurancePolicy.getCost());
        insurancePolicy.setType(insurancePolicy.getType() == null ? saved.getType() : insurancePolicy.getType());
        insurancePolicy.setValidate(insurancePolicy.getValidate() == null ? saved.getValidate()
                : insurancePolicy.getValidate());

        return insurancePolicyRepository.save(insurancePolicy);
    }
}
