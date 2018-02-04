package lych.trucks.domain.service;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultCustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    private static final String CUSTOMER_NAME_CONTENT = "customer name";

    private Integer customerIdContent;

    @Before
    public void setUp() {

        customerRepository.deleteAll();

        final Customer customer = new Customer();

        customer.setCustomerName(CUSTOMER_NAME_CONTENT);

        customerIdContent = customerRepository.save(customer).getCustomerId();
    }

    @Test
    public void create() {

        final Customer customer = new Customer();

        final String content = "new";

        customer.setCustomerName(content);

        assertThat(customerService.createCustomer(customer).getCustomerName(), Is.is(content));
    }

    @Test
    public void fetch() {

        assertThat(customerService.fetchCustomer(customerIdContent).getCustomerName(), Is.is(CUSTOMER_NAME_CONTENT));
    }

    @Test
    public void delete() {

        customerService.deleteCustomer(customerIdContent);

        assertThat(customerRepository.exists(customerIdContent), Is.is(false));
    }

    @Test
    public void update() {

        final String content = "update";

        final Customer customer = customerRepository.findOne(customerIdContent);

        customer.setAddress(content);

        customerService.updateCustomer(customer);

        assertThat(customerRepository.findOne(customerIdContent).getAddress(), Is.is(content));
    }

    @Test
    public void fetchByCustomerName() {

        assertThat(customerService.fetchCustomerByCustomerName(CUSTOMER_NAME_CONTENT).getCustomerName(), Is.is(CUSTOMER_NAME_CONTENT));
    }

}
