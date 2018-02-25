package lych.trucks.application.security.service.jwt;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Token auth service.
 */
public interface TokenAuthenticationService {

    Authentication authenticate(HttpServletRequest request);
}
