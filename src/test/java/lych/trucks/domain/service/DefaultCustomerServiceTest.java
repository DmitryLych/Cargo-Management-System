package lych.trucks.domain.service;

import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DefaultCustomerService customerService;

    private Customer customer;

    private static final String CUSTOMER_NAME_CONTENT = "customer name";

    private static final Integer CUSTOMER_ID = 1;

    @Before
    public void setUp() {
        customer = new Customer();

        customer.setCustomerName(CUSTOMER_NAME_CONTENT);
        customer.setCustomerId(CUSTOMER_ID);
    }

    @Test
    public void create() {

        when(customerRepository.save(customer)).thenReturn(customer);

        final Customer saved = customerService.createCustomer(customer);

        verify(customerRepository).save(customer);

        assertThat(saved.getCustomerName(), is(CUSTOMER_NAME_CONTENT));
    }

    @Test(expected = IllegalStateException.class)
    public void createCustomer_call_nullCustomer_expect_IllegalArgument() {
        customerService.createCustomer(null);
    }

    @Test
    public void fetch() {

        when(customerRepository.findOne(CUSTOMER_ID)).thenReturn(customer);

        final Customer fetchedCustomer = customerService.fetchCustomer(CUSTOMER_ID);

        verify(customerRepository).findOne(CUSTOMER_ID);

        assertThat(fetchedCustomer.getCustomerName(), is(CUSTOMER_NAME_CONTENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchCustomer_call_incorrectId_expect_IllegalArgument() {
        customerService.fetchCustomer(CUSTOMER_ID);
    }

    @Test
    public void delete() {

        when(customerRepository.findOne(CUSTOMER_ID)).thenReturn(customer);

        final Customer deletedCustomer = customerService.deleteCustomer(CUSTOMER_ID);

        verify(customerRepository).findOne(CUSTOMER_ID);
        verify(customerRepository).delete(CUSTOMER_ID);

        assertThat(deletedCustomer.getCustomerName(), is(CUSTOMER_NAME_CONTENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCustomer_call_incorrectId_expect_IllegalArgument() {
        customerService.deleteCustomer(CUSTOMER_ID);
    }

    @Test
    public void update() {

        final String content = "update";

        when(customerRepository.findOne(CUSTOMER_ID)).thenReturn(customer);

        customer.setCustomerName(content);

        when(customerRepository.save(customer)).thenReturn(customer);

        final Customer updated = customerService.updateCustomer(customer);

        verify(customerRepository).findOne(CUSTOMER_ID);
        verify(customerRepository).save(customer);

        assertThat(updated.getCustomerName(), is(content));
    }

    @Test(expected = IllegalStateException.class)
    public void updateCustomer_call_nullCustomer_expect_IllegalState() {
        customerService.updateCustomer(null);
    }

    @Test
    public void fetchByCustomerName() {

        when(customerRepository.findByCustomerName(CUSTOMER_NAME_CONTENT)).thenReturn(customer);

        final Customer fetchedCustomer = customerService.fetchCustomerByCustomerName(CUSTOMER_NAME_CONTENT);

        verify(customerRepository).findByCustomerName(CUSTOMER_NAME_CONTENT);

        assertThat(fetchedCustomer.getCustomerName(), is(CUSTOMER_NAME_CONTENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fetchByCustomerName_call_incorrectName_expect_IllegalArgument() {
        customerService.fetchCustomerByCustomerName(CUSTOMER_NAME_CONTENT);
    }
}
