package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link CustomerService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(final Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer fetchCustomer(final Integer customerId) {
        return customerRepository.findOne(customerId);
    }

    @Override
    public Customer deleteCustomer(final Integer customerId) {

        final Customer customer = customerRepository.findOne(customerId);

        customerRepository.delete(customerId);

        return customer;
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Customer updateCustomer(final Customer customer) {

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

        return customerRepository.findByCustomerName(customerName);
    }
}
