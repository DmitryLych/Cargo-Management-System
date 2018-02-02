package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.CustomerRequest;
import lych.trucks.application.dto.response.CustomerResponse;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.service.CustomerService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link Customer}.
 */
@RestController
@RequestMapping("/cargo/v1/customers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final DozerBeanMapper dozerBeanMapper;

    private final CustomerService customerService;

    /**
     * Method for create customer.
     *
     * @param request {@link CustomerRequest} request.
     * @return {@link CustomerResponse} response mapped from created customer.
     */
    @PostMapping
    public ResponseEntity createCustomer(@RequestBody final CustomerRequest request) {

        final Customer customerToSave = dozerBeanMapper.map(request, Customer.class);

        final Customer customerToResponse = customerService.create(customerToSave);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update customer.
     *
     * @param request {@link CustomerRequest} request.
     * @return @link CustomerResponse} response mapped from updated customer.
     */
    @PutMapping
    public ResponseEntity updateCustomer(@RequestBody final CustomerRequest request) {

        final Customer customerToUpdate = dozerBeanMapper.map(request, Customer.class);

        final Customer customerToResponse = customerService.update(customerToUpdate);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch some customer.
     *
     * @param customerId Customer customerId.
     * @return {@link CustomerResponse} response mapped from customer which found.
     */
    @GetMapping(path = "/{customerId}")
    public ResponseEntity fetchCustomer(@PathVariable final Integer customerId) {

        final Customer customerToResponse = customerService.fetch(customerId);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete some customer.
     *
     * @param customerId Customer customerId.
     * @return {@link CustomerResponse} response mapped from deleted customer.
     */
    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable final Integer customerId) {

        final Customer customerToResponse = customerService.delete(customerId);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch all customers.
     *
     * @return list of {@link CustomerResponse} responses mapped from customers which found.
     */
    @GetMapping
    public ResponseEntity fetchAllCustomers() {

        final List<Customer> customersToResponse = customerService.fetchAll();

        final List<CustomerResponse> response = Optional.ofNullable(customersToResponse)
                .map(customers -> customers.stream()
                        .map(customer -> dozerBeanMapper.map(customer, CustomerResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch customer by customer name.
     *
     * @param customerName {@link Customer} customerName.
     * @return {@link CustomerResponse} response mapped from customer which found.
     */
    @GetMapping(path = "/customerName/{customerName}")
    public ResponseEntity fetchCustomerByCustomerName(@PathVariable final String customerName) {

        final Customer customerToResponse = customerService.fetchByCustomerName(customerName);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }
}
