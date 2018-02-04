package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        final Customer customer = customerService.fetchCustomer(customerId);

        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Order updateOrder(final Order order) {

        final Order saved = orderRepository.findOne(order.getOrderId());

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
        return orderRepository.findOne(orderId);
    }

    @Override
    public Order deleteOrder(final Integer orderId) {

        final Order order = orderRepository.findOne(orderId);

        orderRepository.delete(orderId);

        return order;
    }

    @Override
    public List<Order> fetchOrdersByDriver(final Integer driverId) {
        return orderRepository.findByDriver(driverId);
    }

    @Override
    public List<Order> fetchOrdersByCustomer(final Integer customerId) {
        return orderRepository.findByCustomer(customerId);
    }

    @Override
    public List<Order> fetchOrdersByIssuedAndCustomer(final boolean issued, final Integer customerId) {
        return orderRepository.findByIssuedAndCustomer(issued, customerId);
    }

    @Override
    public List<Order> fetchOrdersByCompletedAndCustomer(final boolean completed, final Integer customerId) {
        return orderRepository.findByCompletedAndCustomer(completed, customerId);
    }

    @Override
    public List<Order> fetchOrdersByPaidAndCustomer(final boolean paid, final Integer customerId) {
        return orderRepository.findByPaidAndCustomer(paid, customerId);
    }
}
