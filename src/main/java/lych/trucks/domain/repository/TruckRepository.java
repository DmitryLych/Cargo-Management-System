package lych.trucks.domain.repository;

import lych.trucks.domain.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Integer> {
}
