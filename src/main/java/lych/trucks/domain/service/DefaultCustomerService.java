package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link CustomerService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> fetchAllCustomers() {

        log.info("Customers displayed.");

        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(final Customer customer) {

        log.info("Customer created.");

        return customerRepository.save(customer);
    }

    @Override
    public Customer fetchCustomer(final Integer customerId) {

        log.info("Customer displayed.");

        return customerRepository.findOne(customerId);
    }

    @Override
    public Customer deleteCustomer(final Integer customerId) {

        log.info("Customer deleted.");

        final Customer customer = customerRepository.findOne(customerId);

        customerRepository.delete(customerId);

        return customer;
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Customer updateCustomer(final Customer customer) {

        log.info("Customer updated");

        final Customer saved = customerRepository.findOne(customer.getCustomerId());

        customer.setAddress(customer.getAddress() == null ? saved.getAddress() : customer.getAddress());
        customer.setCustomerName(customer.getCustomerName() == null ? saved.getCustomerName()
                : customer.getCustomerName());
        customer.setCompanyTelephoneNumber(customer.getCompanyTelephoneNumber() == null
                ? saved.getCompanyTelephoneNumber() : customer.getCompanyTelephoneNumber());
        customer.setEmail(customer.getEmail() == null ? saved.getEmail() : customer.getEmail());
        customer.setMobileTelephoneNumber(customer.getMobileTelephoneNumber() == null
                ? saved.getMobileTelephoneNumber() : customer.getMobileTelephoneNumber());
        customer.setOrders(customer.getOrders() == null ? saved.getOrders() : customer.getOrders());

        return customerRepository.save(customer);
    }

    @Override
    public Customer fetchCustomerByCustomerName(final String customerName) {

        log.info("Customer found by customer name.");

        return customerRepository.findByCustomerName(customerName);
    }
}
