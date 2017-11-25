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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final DozerBeanMapper dozerBeanMapper;

    private final CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final CustomerRequest request) {

        final Customer customerToSave = dozerBeanMapper.map(request, Customer.class);

        final Customer customerToResponse = customerService.create(customerToSave);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final CustomerRequest request) {

        final Customer customerToUpdate = dozerBeanMapper.map(request, Customer.class);

        final Customer customerToResponse = customerService.update(customerToUpdate);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer customerId) {

        final Customer customerToResponse = customerService.fetch(customerId);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(final Integer customerId) {

        final Customer customerToResponse = customerService.fetch(customerId);

        customerService.delete(customerId);

        final CustomerResponse response = dozerBeanMapper.map(customerToResponse, CustomerResponse.class);

        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity fetchAll() {

        final List<CustomerResponse> response = new ArrayList<>();

        customerService.fetchAll().forEach(customer ->
                response.add(dozerBeanMapper.map(customer, CustomerResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
