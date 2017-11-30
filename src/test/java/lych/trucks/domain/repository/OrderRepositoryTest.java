package lych.trucks.domain.repository;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Integer customerIdContent;

    private Integer driverIdContent;

    private static final boolean ISSUED_CONTENT = true;

    private static final boolean COMPLETED_CONTENT = true;

    private static final boolean PAID_CONTENT = true;

    @Before
    public void setUp() {

        final Driver driver = new Driver();

        driverIdContent = driverRepository.save(driver).getId();

        final Customer customer = new Customer();

        customerIdContent = customerRepository.save(customer).getCustomerId();

        final Order order = new Order();

        order.setPaid(PAID_CONTENT);
        order.setCompleted(COMPLETED_CONTENT);
        order.setIssued(ISSUED_CONTENT);
        order.setOwnerCustomerId(customerIdContent);
        order.setOwnerIdDriver(driverIdContent);

        orderRepository.save(order);
    }

    @Test
    public void findByOwnerIdDriver() {

        final List<Order> foundOrders = orderRepository.findByOwnerIdDriver(driverIdContent);

        foundOrders.forEach(order -> assertThat(order.getOwnerIdDriver(), Is.is(driverIdContent)));
    }

    @Test
    public void findByOwnerCustomerId() {

        final List<Order> foundOrders = orderRepository.findByOwnerCustomerId(customerIdContent);

        foundOrders.forEach(order -> assertThat(order.getOwnerCustomerId(), Is.is(customerIdContent)));
    }

    @Test
    public void findByIssuedAndOwnerCustomerId() {

        final List<Order> foundOrders = orderRepository.findByIssuedAndOwnerCustomerId(ISSUED_CONTENT, customerIdContent);

        foundOrders.forEach(order -> assertThat(order.isIssued(), Is.is(ISSUED_CONTENT)));
    }

    @Test
    public void findByCompletedAndOwnerCustomerId() {

        final List<Order> foundOrders = orderRepository.findByCompletedAndOwnerCustomerId(COMPLETED_CONTENT, customerIdContent);

        foundOrders.forEach(order -> assertThat(order.isCompleted(), Is.is(COMPLETED_CONTENT)));
    }

    @Test
    public void findByPaidAndOwnerCustomerId() {

        final List<Order> foundOrders = orderRepository.findByPaidAndOwnerCustomerId(PAID_CONTENT, customerIdContent);

        foundOrders.forEach(order -> assertThat(order.isPaid(), Is.is(PAID_CONTENT)));
    }

}
