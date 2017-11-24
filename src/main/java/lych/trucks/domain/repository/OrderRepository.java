package lych.trucks.domain.repository;

import lych.trucks.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE owner_id_driver=?1", nativeQuery = true)
    Order findByOwnerIdDriver(Integer driverId);

    @Query(value = "SELECT * FROM orders WHERE owner_customer_id=?1", nativeQuery = true)
    List<Order> findByOwnerCustomerId(Integer customerId);

    @Query(value = "SELECT * FROM orders WHERE issued=?1", nativeQuery = true)
    List<Order> findByIssued(boolean issued);

    @Query(value = "SELECT * FROM orders WHERE completed=?1", nativeQuery = true)
    List<Order> findByCompleted(boolean completed);
}
