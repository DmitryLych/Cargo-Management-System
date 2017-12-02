package lych.trucks.domain.repository;

import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interface for {@link Truck} work with database.
 */
public interface TruckRepository extends JpaRepository<Truck, Integer> {

    /**
     * Method for find {@link Truck} by driver id.
     *
     * @param driverId {@link Driver} driverId.
     * @return truck which found.
     */
    @Query(value = "SELECT * FROM trucks WHERE truck_fk=?1", nativeQuery = true)
    Truck findByTruckFk(Integer driverId);

    /**
     * Method for find {@link Truck} by register sign.
     *
     * @param registerSign {@link Truck} registerSign.
     * @return truck which found.
     */
    @Query(value = "SELECT * FROM trucks WHERE register_sign=?1", nativeQuery = true)
    Truck findByRegisterSign(String registerSign);

    /**
     * Method for find {@link Truck} by body number.
     *
     * @param bodyNumber {@link Truck} bodyNumber.
     * @return truck which found.
     */
    @Query(value = "SELECT * FROM trucks WHERE body_number=?1", nativeQuery = true)
    Truck findByBodyNumber(String bodyNumber);
}
