package lych.trucks.domain.repository;

import lych.trucks.domain.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Integer> {
}
