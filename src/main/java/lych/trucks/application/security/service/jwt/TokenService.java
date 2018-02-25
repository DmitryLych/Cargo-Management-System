package lych.trucks.application.security.service.jwt;

import lych.trucks.application.security.model.User;

/**
 * Token service.
 */
public interface TokenService {

    String getToken(User user);
}
