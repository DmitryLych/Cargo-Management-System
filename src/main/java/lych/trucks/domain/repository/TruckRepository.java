package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with database.
 */
public interface TruckRepository extends JpaRepository<Truck, Integer> {

    /**
     * Method for find {@link Truck} by driver id.
     *
     * @param driverId {@link Driver} driverId.
     * @return truck which found.
     */
    Truck findByOwnerIdForTruck(Integer driverId);
}
