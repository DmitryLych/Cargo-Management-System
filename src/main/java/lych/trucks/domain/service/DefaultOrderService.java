package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Order create(final Integer customerId, final Order order) {

        log.info("Order created.");

        order.setOwnerCustomerId(customerId);

        return orderRepository.save(order);
    }

    @Override
    public Order update(final Order order) {

        log.info("Order updated.");

        final Order saved = orderRepository.findOne(order.getOrderId());

        order.setOwnerCustomerId(saved.getOwnerCustomerId());
        order.setCoast(order.getCoast() == 0 ? saved.getCoast() : order.getCoast());
        order.setOwnerIdDriver(order.getOwnerIdDriver() == null ? saved.getOwnerIdDriver() : order.getOwnerIdDriver());
        order.setCompleted(order.isCompleted() ? order.isCompleted() : saved.isCompleted());
        order.setIssued(order.isIssued() ? order.isIssued() : saved.isIssued());
        order.setDownloadAddress(order.getDownloadAddress() == null ? saved.getDownloadAddress() : order.getDownloadAddress());
        order.setGoods(order.getGoods() == null ? saved.getGoods() : order.getGoods());
        order.setPaid(order.isPaid() ? order.isPaid() : saved.isPaid());
        order.setUnloadingAddress(order.getUnloadingAddress() == null ? saved.getUnloadingAddress() : order.getUnloadingAddress());

        return order;
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

        return orderRepository.findByOwnerIdDriver(driverId);
    }

    @Override
    public List<Order> fetchByCustomer(final Integer customerId) {

        log.info("Order fetched by customer.");

        return orderRepository.findByOwnerCustomerId(customerId);
    }

    @Override
    public List<Order> fetchByIssued(final boolean issued, final Integer customerId) {

        log.info("Fetched issued orders.");

        return orderRepository.findByIssuedAndOwnerCustomerId(issued, customerId);
    }

    @Override
    public List<Order> fetchByCompleted(final boolean completed, final Integer customerId) {

        log.info("Fetched completed orders.");

        return orderRepository.findByCompletedAndOwnerCustomerId(completed, customerId);
    }

    @Override
    public List<Order> fetchByPaid(final boolean paid, final Integer customerId) {

        log.info("Fetched paid orders.");

        return orderRepository.findByPaidAndOwnerCustomerId(paid, customerId);
    }
}
