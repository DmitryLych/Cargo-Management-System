package lych.trucks.domain.service;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.CustomerRepository;
import lych.trucks.domain.repository.DriverRepository;
import lych.trucks.domain.repository.OrderRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultOrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    private Integer driverIdContent;

    private Integer customerIdContent;

    private Integer orderIdContent;

    private static final boolean ISSUED_CONTENT = true;

    private static final boolean COMPLETED_CONTENT = true;

    private static final boolean PAID_CONTENT = true;

    @Before
    public void setUp() {

        customerIdContent = customerRepository.save(new Customer()).getCustomerId();

        driverIdContent = driverRepository.save(new Driver()).getId();

        final Order order = new Order();

        order.setCompleted(COMPLETED_CONTENT);
        order.setIssued(ISSUED_CONTENT);
        order.setPaid(PAID_CONTENT);
        order.setOwnerCustomerId(customerIdContent);
        order.setOwnerIdDriver(driverIdContent);

        orderIdContent = orderRepository.save(order).getOrderId();
    }

    @Test
    public void create() {

        final String content = "created";

        final Order order = new Order();

        order.setDownloadAddress(content);

        final Integer newId = orderService.create(customerIdContent, order).getOrderId();

        assertThat(orderRepository.findOne(newId).getDownloadAddress(), Is.is(content));
    }

    @Test
    public void update() {

        final String content = "updated";

        final Order order = orderRepository.findOne(orderIdContent);

        order.setDownloadAddress(content);

        orderService.update(order);

        assertThat(orderRepository.findOne(orderIdContent).getDownloadAddress(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(orderService.fetch(orderIdContent).isCompleted(), Is.is(COMPLETED_CONTENT));
    }

    @Test
    public void delete() {

        orderService.delete(orderIdContent);

        assertThat(orderRepository.exists(orderIdContent), Is.is(false));
    }

    @Test
    public void fetchByDriver() {

        final List<Order> orders = orderService.fetchByDriver(driverIdContent);

        orders.forEach(order -> assertThat(order.getOwnerIdDriver(), Is.is(driverIdContent)));
    }

    @Test
    public void fetchByCustomer() {

        final List<Order> orders = orderService.fetchByCustomer(customerIdContent);

        orders.forEach(order -> assertThat(order.getOwnerCustomerId(), Is.is(customerIdContent)));
    }

    @Test
    public void fetchByIssued() {

        final List<Order> orders = orderService.fetchByIssued(ISSUED_CONTENT, customerIdContent);

        orders.forEach(order -> assertThat(order.getOwnerCustomerId(), Is.is(customerIdContent)));
    }

    @Test
    public void fetchByCompleted() {

        final List<Order> orders = orderService.fetchByCompleted(COMPLETED_CONTENT, customerIdContent);

        orders.forEach(order -> assertThat(order.getOwnerCustomerId(), Is.is(customerIdContent)));
    }

    @Test
    public void fetchByPaid() {

        final List<Order> orders = orderService.fetchByPaid(PAID_CONTENT, customerIdContent);

        orders.forEach(order -> assertThat(order.getOwnerCustomerId(), Is.is(customerIdContent)));
    }
}
