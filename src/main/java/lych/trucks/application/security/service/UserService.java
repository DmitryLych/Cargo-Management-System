package lych.trucks.application.security.service;

import lych.trucks.application.security.model.User;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;

import java.util.List;

/**
 * User service.
 */
public interface UserService {

    User createUser(User user);

    User updateUser(User user, Company company, Driver driver, Customer customer);

    void deleteUser(Integer userId);

    User getUserByUsername(String username);

    User fetchUser(Integer userId);

    List<User> listUsers();
}
