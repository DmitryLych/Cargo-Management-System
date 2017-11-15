package lych.trucks.domain.service;

import lych.trucks.domain.model.InsurancePolicy;

import java.util.List;

public interface InsurancePolicyService {

    List<InsurancePolicy> fetchAll();

    InsurancePolicy create(InsurancePolicy insurancePolicy);

    InsurancePolicy fetch(Integer id);

    void delete(Integer id);

    InsurancePolicy update(InsurancePolicy insurancePolicy);
}
