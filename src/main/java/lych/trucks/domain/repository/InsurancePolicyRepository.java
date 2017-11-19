package lych.trucks.domain.repository;

import lych.trucks.domain.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for work with database.
 */
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

    List<InsurancePolicy> findAllByOwnerIdForInsurancePolicy(Integer driverId);
}
