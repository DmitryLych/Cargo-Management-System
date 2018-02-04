package lych.trucks.domain.service;

import lych.trucks.domain.model.Customer;

import java.util.List;

/**
 * Service for {@link Customer} work with database.
 */
public interface CustomerService {

    /**
     * Method for fetch all customers.
     *
     * @return list of customers.
     */
    List<Customer> fetchAllCustomers();

    /**
     * Method for create customer.
     *
     * @param customer Customer customer.
     * @return created customer.
     */
    Customer createCustomer(Customer customer);

    /**
     * Method for fetch some customer.
     *
     * @param customerId Customer customerId.
     * @return customer which found.
     */
    Customer fetchCustomer(Integer customerId);

    /**
     * Method for delete some customer.
     *
     * @param customerId Customer customerId.
     * @return deleted customer.
     */
    Customer deleteCustomer(Integer customerId);

    /**
     * Method for update some customer.
     *
     * @param customer Customer customer.
     * @return updated customer.
     */
    Customer updateCustomer(Customer customer);

    /**
     * Method for fetch customer by name.
     *
     * @param customerName Customer customerName.
     * @return customer which found.
     */
    Customer fetchCustomerByCustomerName(String customerName);
}
