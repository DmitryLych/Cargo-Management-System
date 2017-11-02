package lych.trucks.repository;

import lych.trucks.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck,Integer>{
}
