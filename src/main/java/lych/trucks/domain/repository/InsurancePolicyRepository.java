package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for work with database.
 */
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

    /**
     * Method for find {@link InsurancePolicy} by driver id.
     *
     * @param driverId {@link Driver}
     * @return insurance policy which found.
     */
    List<InsurancePolicy> findAllByOwnerIdForInsurancePolicy(Integer driverId);
}
