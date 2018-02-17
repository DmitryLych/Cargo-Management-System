package lych.trucks.domain.repository;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
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

        final Driver driver = driverRepository.save(new Driver());

        driverIdContent = driver.getId();

        final Customer customer = customerRepository.save(new Customer());

        customerIdContent = customer.getCustomerId();

        final Order order = new Order();

        order.setPaid(PAID_CONTENT);
        order.setCompleted(COMPLETED_CONTENT);
        order.setIssued(ISSUED_CONTENT);
        order.setCustomer(customer);
        order.setDriver(driver);

        orderRepository.save(order);
    }

    @Test
    public void findByDriver() {

        final List<Order> foundOrders = orderRepository.findByDriver(driverIdContent);

        foundOrders.forEach(order -> assertThat(order.getDriver().getId(), is(driverIdContent)));
    }

    @Test
    public void findByCustomer() {

        final List<Order> foundOrders = orderRepository.findByCustomer(customerIdContent);

        foundOrders.forEach(order -> assertThat(order.getCustomer().getCustomerId(), is(customerIdContent)));
    }

    @Test
    public void findByIssuedAndCustomer() {

        final List<Order> foundOrders = orderRepository.findByIssuedAndCustomer(ISSUED_CONTENT, customerIdContent);

        foundOrders.forEach(order -> assertThat(order.isIssued(), is(ISSUED_CONTENT)));
    }

    @Test
    public void findByCompletedAndCustomer() {

        final List<Order> foundOrders = orderRepository
                .findByCompletedAndCustomer(COMPLETED_CONTENT, customerIdContent);

        foundOrders.forEach(order -> assertThat(order.isCompleted(), is(COMPLETED_CONTENT)));
    }

    @Test
    public void findByPaidAndCustomer() {

        final List<Order> foundOrders = orderRepository
                .findByPaidAndCustomer(PAID_CONTENT, customerIdContent);

        foundOrders.forEach(order -> assertThat(order.isPaid(), is(PAID_CONTENT)));
    }
}
