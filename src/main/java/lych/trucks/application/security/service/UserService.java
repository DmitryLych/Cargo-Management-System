package lych.trucks.application.security.service;

import lych.trucks.application.security.model.User;

import java.util.List;

/**
 * User service.
 */
public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Integer userId);

    User getUserByUsername(String username);

    User fetchUser(Integer userId);

    List<User> listUsers();
}
