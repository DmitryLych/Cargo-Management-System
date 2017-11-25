package lych.trucks.domain.service;

import lych.trucks.domain.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> fetchAll();

    Customer create(Customer customer);

    Customer fetch(Integer customerId);

    void delete(Integer customerId);

    Customer update(Customer customer);
}
