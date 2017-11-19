package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    List<Driver> findAllByOwnerId(Integer ownerId);
}
