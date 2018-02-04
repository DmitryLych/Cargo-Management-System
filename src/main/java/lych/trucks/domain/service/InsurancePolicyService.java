package lych.trucks.domain.service;

import lych.trucks.application.dto.response.InsurancePolicyResponse;
import lych.trucks.domain.model.InsurancePolicy;

import java.util.List;

/**
 * Service for {@link InsurancePolicy} work with database.
 */
public interface InsurancePolicyService {

    /**
     * Method for display all Insurance policy.
     *
     * @param driverId Driver driverId.
     * @return List of insurance polices.
     */
    List<InsurancePolicy> fetchAllInsurancePolicies(Integer driverId);

    /**
     * Method for create Insurance policy.
     *
     * @param driverId        Driver driverId.
     * @param insurancePolicy InsurancePolicy insurancePolicy.
     * @return created insurance policy.
     */
    InsurancePolicy createInsurancePolicy(Integer driverId, InsurancePolicy insurancePolicy);

    /**
     * Method for display some insurance policy.
     *
     * @param id InsurancePolicy id.
     * @return found insurance policy.
     */
    InsurancePolicy fetchInsurancePolicy(Integer id);

    /**
     * Method for delete insurance policy.
     *
     * @param id InsurancePolicy id.
     * @return deleted insurance policy.
     */
    InsurancePolicy deleteInsurancePolicy(Integer id);

    /**
     * Method for update insurance policy.
     *
     * @param insurancePolicy InsurancePolicy insurancePolicy.
     * @return updated insurance policy.
     */
    InsurancePolicy updateInsurancePolicy(InsurancePolicy insurancePolicy);

    /**
     * Method for fetch insurance policies by validate.
     *
     * @param validate {@link InsurancePolicy} validate.
     * @return list of {@link InsurancePolicyResponse} mapped from list of insurance policies which found.
     */
    List<InsurancePolicy> fetchInsurancePoliciesByValidate(Long validate);

    /**
     * Method for fetch insurance policies by type.
     *
     * @param type {@link InsurancePolicy} type.
     * @return list of {@link InsurancePolicyResponse} mapped from list of insurance policies which found.
     */
    List<InsurancePolicy> fetchInsurancePoliciesByType(String type);
}
