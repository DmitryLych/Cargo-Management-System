package lych.trucks.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.model.InsurancePolicy;
import lych.trucks.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultInsurancePolicyService implements InsurancePolicyService {

    private final InsurancePolicyRepository insurancePolicyRepository;

    @Override
    public List<InsurancePolicy> list() {
        return insurancePolicyRepository.findAll();
    }

    @Override
    public InsurancePolicy create(InsurancePolicy insurancePolicy) {
        return insurancePolicyRepository.save(insurancePolicy);
    }

    @Override
    public InsurancePolicy fetch(Integer id) {
        return insurancePolicyRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {

        insurancePolicyRepository.delete(id);
    }

    @Override
    public InsurancePolicy update(InsurancePolicy insurancePolicy) {
        return insurancePolicyRepository.save(insurancePolicy);
    }
}
