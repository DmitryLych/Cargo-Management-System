package lych.trucks.service;

import lych.trucks.model.InsurancePolicy;

import java.util.List;

public interface InsurancePolicyService {

    List<InsurancePolicy> list();

    InsurancePolicy create(InsurancePolicy insurancePolicy);

    InsurancePolicy fetch(Integer id);

    void delete(Integer id);

    InsurancePolicy update(InsurancePolicy insurancePolicy);
}
