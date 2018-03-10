package lych.trucks.application.security.service.jwt;

import lych.trucks.application.security.model.User;

import java.util.List;

/**
 * Token service.
 */
public interface TokenService {

    List<String> getToken(User user);
}
