package lych.trucks.repository;

import lych.trucks.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense,Integer> {
}
