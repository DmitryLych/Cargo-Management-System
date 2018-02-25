package lych.trucks.application.security.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.model.User;
import lych.trucks.application.security.model.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Optional;

import static java.util.Calendar.getInstance;

/**
 * Implementation of {@link TokenAuthenticationService}.
 */
@SuppressWarnings("PMD")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTokenAuthenticationService implements TokenAuthenticationService {

    private final UserDetailsService userDetailsService;

    @Value("${secret.key}")
    private String key;

    @Value("${auth.header.name}")
    private String headerName;

    @Override
    public Authentication authenticate(final HttpServletRequest request) {

        final String token = Optional.ofNullable(request.getHeader(headerName))
                .orElse(null);

        if (token == null) {
            return null;
        }

        final Jws<Claims> tokenData = parseToken(token);

        if (tokenData == null) {
            return null;
        }

        final Long validationTime = Optional.ofNullable((Long) tokenData.getBody().get("validateTime"))
                .orElseThrow(() -> new IllegalStateException("Incorrect token format."));

        final Calendar calendar = getInstance();

        final Long nowTime = calendar.getTime().getTime();

        return nowTime < validationTime
                ? new UserAuthentication(getUserFromToken(tokenData))
                : null;
    }

    private Jws<Claims> parseToken(final String token) {

        return Optional.ofNullable(Jwts.parser().setSigningKey(key).parseClaimsJws(token))
                .orElseThrow(() -> new IllegalStateException("Incorrect Token."));
    }

    private User getUserFromToken(final Jws<Claims> tokenData) throws RuntimeException {
        return Optional.ofNullable((User) userDetailsService
                .loadUserByUsername(tokenData.getBody().get("username").toString()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
