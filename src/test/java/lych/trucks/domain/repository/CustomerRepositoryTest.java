package lych.trucks.domain.repository;

import lych.trucks.domain.model.Customer;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private static final String CONTENT = "customer";

    @Before
    public void setUp() {

        final Customer customer = new Customer();

        customer.setCustomerName(CONTENT);

        customerRepository.save(customer);
    }

    @Test
    public void findByCustomerName() {

        final Customer foundCustomer = customerRepository.findByCustomerName(CONTENT);

        assertThat(foundCustomer.getCustomerName(), Is.is(CONTENT));
    }
}
