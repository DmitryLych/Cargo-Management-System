package lych.trucks.domain.repository;

import lych.trucks.domain.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Integer> {
}
