package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Interface for {@link InsurancePolicy} work with database.
 */
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

    /**
     * Method for find {@link InsurancePolicy} by driver id.
     *
     * @param driverId {@link Driver}
     * @return insurance policy which found.
     */
    @Query(value = "SELECT * FROM insurance_policies WHERE driver_id=?1", nativeQuery = true)
    List<InsurancePolicy> findAllByDriver(Integer driverId);

    /**
     * Method for find insurance policy by validate.
     *
     * @param validate InsurancePolicy validate.
     * @return list of insurance policy which found.
     */
    @Query(value = "SELECT * FROM insurance_policies WHERE validate=?1", nativeQuery = true)
    List<InsurancePolicy> findByValidate(Date validate);

    /**
     * Method for find insurance policy by type.
     *
     * @param type InsurancePolicy type.
     * @return list of insurance policy which found.
     */
    @Query(value = "SELECT * FROM insurance_policies WHERE type_insurance_policy=?1", nativeQuery = true)
    List<InsurancePolicy> findByType(String type);
}
