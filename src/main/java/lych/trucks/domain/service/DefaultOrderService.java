package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link OrderService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerService customerService;

    @Override
    public Order createOrder(final Integer customerId, final Order order) {
        validateOrder(order);

        final Customer customer = customerService.fetchCustomer(customerId);

        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Order updateOrder(final Order order) {
        validateOrder(order);

        final Order saved = fetchOrder(order.getOrderId());

        order.setCustomer(saved.getCustomer());
        order.setCoast(order.getCoast() == null ? saved.getCoast() : order.getCoast());
        order.setDriver(order.getDriver() == null ? saved.getDriver() : order.getDriver());
        order.setCompleted(order.isCompleted() ? order.isCompleted() : saved.isCompleted());
        order.setIssued(order.isIssued() ? order.isIssued() : saved.isIssued());
        order.setDownloadAddress(order.getDownloadAddress() == null ? saved.getDownloadAddress()
                : order.getDownloadAddress());
        order.setGoods(order.getGoods() == null ? saved.getGoods() : order.getGoods());
        order.setPaid(order.isPaid() ? order.isPaid() : saved.isPaid());
        order.setUnloadingAddress(order.getUnloadingAddress() == null ? saved.getUnloadingAddress()
                : order.getUnloadingAddress());

        return orderRepository.save(order);
    }

    @Override
    public Order fetchOrder(final Integer orderId) {
        return Optional.ofNullable(orderRepository.findOne(orderId))
                .orElseThrow(() -> new IllegalArgumentException("Order not found. "
                        + "Order with this Id: '" + orderId + "' not exists."));
    }

    @Override
    public Order deleteOrder(final Integer orderId) {

        final Order order = fetchOrder(orderId);

        orderRepository.delete(orderId);

        return order;
    }

    @Override
    public List<Order> fetchOrdersByDriver(final Integer driverId) {
        return Optional.ofNullable(orderRepository.findByDriver(driverId))
                .orElseThrow(() -> new IllegalArgumentException("Orders not found. "
                        + "Driver with Id: '" + driverId + "' don`t have orders."));
    }

    @Override
    public List<Order> fetchOrdersByCustomer(final Integer customerId) {
        return Optional.ofNullable(orderRepository.findByCustomer(customerId))
                .orElseThrow(() -> new IllegalArgumentException("Orders not found. "
                        + "Customer with Id: '" + customerId + "' don`t have orders."));
    }

    @Override
    public List<Order> fetchOrdersByIssuedAndCustomer(final boolean issued, final Integer customerId) {
        return Optional.ofNullable(orderRepository.findByIssuedAndCustomer(issued, customerId))
                .orElseThrow(() -> new IllegalArgumentException("Orders not found. "
                        + "Customer with Id: '" + customerId + "' don`t have orders with issued status: '" + issued + "'"));
    }

    @Override
    public List<Order> fetchOrdersByCompletedAndCustomer(final boolean completed, final Integer customerId) {
        return Optional.ofNullable(orderRepository.findByCompletedAndCustomer(completed, customerId))
                .orElseThrow(() -> new IllegalArgumentException("Orders not found. "
                        + "Customer with Id: '" + customerId + "' don`t have orders with completed status: '" + completed + "'"));
    }

    @Override
    public List<Order> fetchOrdersByPaidAndCustomer(final boolean paid, final Integer customerId) {
        return Optional.ofNullable(orderRepository.findByPaidAndCustomer(paid, customerId))
                .orElseThrow(() -> new IllegalArgumentException("Orders not found. "
                        + "Customer with Id: '" + customerId + "' don`t have orders with paid status: '" + paid + "'"));
    }

    private static void validateOrder(final Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order can`t be null.");
        }
    }
}
