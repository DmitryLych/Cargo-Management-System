package lych.trucks.domain.repository;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for {@link Order} word with database.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Method for find orders by driver.
     *
     * @param driverId {@link Driver} driverId.
     * @return list of orders which found.
     */
    @Query(value = "SELECT * FROM orders WHERE owner_id_driver=?1", nativeQuery = true)
    List<Order> findByOwnerIdDriver(Integer driverId);

    /**
     * Method for find orders by customer.
     *
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    @Query(value = "SELECT * FROM orders WHERE owner_customer_id=?1", nativeQuery = true)
    List<Order> findByOwnerCustomerId(Integer customerId);

    /**
     * Method for found orders by issued and customer.
     *
     * @param issued     {@link Order} issued.
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    @Query(value = "SELECT * FROM orders WHERE issued=?1 AND owner_customer_id=?2", nativeQuery = true)
    List<Order> findByIssuedAndOwnerCustomerId(boolean issued, Integer customerId);

    /**
     * Method for find orders by completed and customer.
     *
     * @param completed  {@link Order} completed.
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    @Query(value = "SELECT * FROM orders WHERE completed=?1 AND owner_customer_id=?2", nativeQuery = true)
    List<Order> findByCompletedAndOwnerCustomerId(boolean completed, Integer customerId);

    /**
     * Method for find orders by paid and customer.
     *
     * @param paid       {@link Order} paid.
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    @Query(value = "SELECT * FROM orders WHERE paid=?1 AND owner_customer_id=?2", nativeQuery = true)
    List<Order> findByPaidAndOwnerCustomerId(boolean paid, Integer customerId);
}
