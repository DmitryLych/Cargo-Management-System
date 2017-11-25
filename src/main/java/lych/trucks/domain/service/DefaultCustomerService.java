package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public List<Customer> fetchAll() {

        log.info("Customers displayed.");

        return customerRepository.findAll();
    }

    @Override
    public Customer create(final Customer customer) {

        log.info("Customer created.");

        return customerRepository.save(customer);
    }

    @Override
    public Customer fetch(final Integer customerId) {

        log.info("Customer displayed.");

        return customerRepository.findOne(customerId);
    }

    @Override
    public void delete(final Integer customerId) {

        log.info("Customer deleted.");

        customerRepository.delete(customerId);
    }

    @Override
    public Customer update(final Customer customer) {

        log.info("Customer updated");

        final Customer saved = customerRepository.findOne(customer.getCustomerId());

        customer.setAddress(customer.getAddress() == null ? saved.getAddress() : customer.getAddress());
        customer.setCompanyName(customer.getCompanyName() == null ? saved.getCompanyName()
                : customer.getCompanyName());
        customer.setCompanyTelephoneNumber(customer.getCompanyTelephoneNumber() == null ?
                saved.getCompanyTelephoneNumber() : customer.getCompanyTelephoneNumber());
        customer.setEmail(customer.getEmail() == null ? saved.getEmail() : customer.getEmail());
        customer.setFirstName(customer.getFirstName() == null ? saved.getFirstName() : customer.getFirstName());
        customer.setLastName(customer.getLastName() == null ? saved.getLastName() : customer.getLastName());
        customer.setMobileTelephoneNumber(customer.getMobileTelephoneNumber() == null ?
                saved.getMobileTelephoneNumber() : customer.getMobileTelephoneNumber());
        customer.setOrders(customer.getOrders() == null ? saved.getOrders() : customer.getOrders());

        return customerRepository.save(customer);
    }
}
