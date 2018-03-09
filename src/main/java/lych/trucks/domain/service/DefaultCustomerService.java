package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.service.UserService;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link CustomerService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    private final UserService userService;

    @Override
    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(final Integer userId, final Customer customer) {
        validateCustomer(customer);

        customer.setUser(userService.fetchUser(userId));
        return customerRepository.save(customer);
    }

    @Override
    public Customer fetchCustomer(final Integer customerId) {
        return Optional.ofNullable(customerRepository.findOne(customerId))
                .orElseThrow(() -> new IllegalArgumentException("Can`t find Customer by Id. Customer with this"
                        + " Id: '" + customerId + "' not exist."));
    }

    @Override
    public Customer deleteCustomer(final Integer customerId) {

        final Customer customer = fetchCustomer(customerId);

        customerRepository.delete(customerId);

        return customer;
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Customer updateCustomer(final Customer customer) {
        validateCustomer(customer);

        final Customer saved = fetchCustomer(customer.getCustomerId());

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
        return Optional.ofNullable(customerRepository.findByCustomerName(customerName))
                .orElseThrow(() -> new IllegalArgumentException("Customer can`t find by Customer name. Customer with "
                        + "Customer name: '" + customerName + "' not exist."));
    }

    private static void validateCustomer(final Customer customer) {

        if (customer == null) {
            throw new IllegalStateException("Customer can`t be null.");
        }
    }
}
