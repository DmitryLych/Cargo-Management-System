package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerService customerService;

    @Override
    public Order create(final Integer customerId, final Order order) {

        log.info("Order created.");

        final Customer customer = customerService.fetch(customerId);

        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Order update(final Order order) {

        log.info("Order updated.");

        final Order saved = orderRepository.findOne(order.getOrderId());

        order.setCustomer(saved.getCustomer());
        order.setCoast(order.getCoast() == 0 ? saved.getCoast() : order.getCoast());
        order.setDriver(order.getDriver() == null ? saved.getDriver() : order.getDriver());
        order.setCompleted(order.isCompleted() ? order.isCompleted() : saved.isCompleted());
        order.setIssued(order.isIssued() ? order.isIssued() : saved.isIssued());
        order.setDownloadAddress(order.getDownloadAddress() == null ? saved.getDownloadAddress() : order.getDownloadAddress());
        order.setGoods(order.getGoods() == null ? saved.getGoods() : order.getGoods());
        order.setPaid(order.isPaid() ? order.isPaid() : saved.isPaid());
        order.setUnloadingAddress(order.getUnloadingAddress() == null ? saved.getUnloadingAddress() : order.getUnloadingAddress());

        return orderRepository.save(order);
    }

    @Override
    public Order fetch(final Integer orderId) {

        log.info("Order fetched.");

        return orderRepository.findOne(orderId);
    }

    @Override
    public Order delete(final Integer orderId) {

        log.info("Order deleted.");

        final Order order = orderRepository.findOne(orderId);

        orderRepository.delete(orderId);

        return order;
    }

    @Override
    public List<Order> fetchByDriver(final Integer driverId) {

        log.info("Order fetched by driver.");

        return orderRepository.findByDriver(driverId);
    }

    @Override
    public List<Order> fetchByCustomer(final Integer customerId) {

        log.info("Order fetched by customer.");

        return orderRepository.findByCustomer(customerId);
    }

    @Override
    public List<Order> fetchByIssuedAndCustomer(final boolean issued, final Integer customerId) {

        log.info("Fetched issued orders.");

        return orderRepository.findByIssuedAndCustomer(issued, customerId);
    }

    @Override
    public List<Order> fetchByCompletedAndCustomer(final boolean completed, final Integer customerId) {

        log.info("Fetched completed orders.");

        return orderRepository.findByCompletedAndCustomer(completed, customerId);
    }

    @Override
    public List<Order> fetchByPaidAndCustomer(final boolean paid, final Integer customerId) {

        log.info("Fetched paid orders.");

        return orderRepository.findByPaidAndCustomer(paid, customerId);
    }
}
