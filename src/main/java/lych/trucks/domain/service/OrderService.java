package lych.trucks.domain.service;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;

import java.util.List;

/**
 * Service for {@link Order} word with database.
 */
public interface OrderService {

    /**
     * Method for create order.
     *
     * @param customerId {@link Customer} customerId.
     * @param order      Order order.
     * @return created order.
     */
    Order createOrder(Integer customerId, Order order);

    /**
     * Method for update some order.
     *
     * @param order Order order.
     * @return updated order.
     */
    Order updateOrder(Order order);

    /**
     * Method for fetch some order by id.
     *
     * @param orderId Order orderId.
     * @return order which found.
     */
    Order fetchOrder(Integer orderId);

    /**
     * Method for delete order by id.
     *
     * @param orderId Order orderId.
     * @return deleted order.
     */
    Order deleteOrder(Integer orderId);

    /**
     * Method for fetch orders by driver.
     *
     * @param driverId {@link Driver} driverId.
     * @return list of orders which found.
     */
    List<Order> fetchOrdersByDriver(Integer driverId);

    /**
     * Method for fetch orders by customer.
     *
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    List<Order> fetchOrdersByCustomer(Integer customerId);

    /**
     * Method for fetch orders by issued and customer.
     *
     * @param issued     Order issued.
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    List<Order> fetchOrdersByIssuedAndCustomer(boolean issued, Integer customerId);

    /**
     * Method for fetch by completed and customer.
     *
     * @param completed  Order completed.
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    List<Order> fetchOrdersByCompletedAndCustomer(boolean completed, Integer customerId);

    /**
     * Method for fetch orders by paid and customer.
     *
     * @param paid       Order paid.
     * @param customerId {@link Customer} customerId.
     * @return list of orders which found.
     */
    List<Order> fetchOrdersByPaidAndCustomer(boolean paid, Integer customerId);
}
