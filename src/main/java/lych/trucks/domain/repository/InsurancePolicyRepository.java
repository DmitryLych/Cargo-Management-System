package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
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
    @Query(value = "SELECT * FROM insurance_policies WHERE owner_id_for_insurance_policy=?1", nativeQuery = true)
    List<InsurancePolicy> findAllByOwnerIdForInsurancePolicy(Integer driverId);

    @Query(value = "SELECT * FROM insurance_policies WHERE validate=?1", nativeQuery = true)
    List<InsurancePolicy> findByValidate(Date validate);

    @Query(value = "SELECT * FROM insurance_policies WHERE type_insurance_policy=?1", nativeQuery = true)
    List<InsurancePolicy> findByType(String type);
}
