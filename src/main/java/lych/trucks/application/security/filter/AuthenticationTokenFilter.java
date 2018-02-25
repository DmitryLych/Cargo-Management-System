package lych.trucks.application.security.filter;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.service.jwt.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Token filter for auth.
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationTokenFilter extends GenericFilterBean {

    private final TokenAuthenticationService authenticationService;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;

        final Authentication authentication = authenticationService.authenticate(httpRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
