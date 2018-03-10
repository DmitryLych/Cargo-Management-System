package lych.trucks.application.security.service.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Implementation of {@link TokenService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTokenService implements TokenService {

    private final UserDetailsService userDetailsService;

    @Value("${secret.key}")
    private String key;

    @Value("${token.expiration.time}")
    private Integer expirationTime;

    @Override
    public List<String> getToken(final User user) {

        final String username = Optional.of(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User must have username."));

        final String password = Optional.of(user.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("User must have password."));

        final User foundUser = (User) userDetailsService.loadUserByUsername(username);

        final Map<String, Object> token = new HashMap<>();

        if (password.equals(foundUser.getPassword())) {

            final Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, expirationTime);

            token.put("authorityType", String.valueOf(foundUser.getAuthorities()));
            token.put("userId", foundUser.getId());
            token.put("username", foundUser.getUsername());
            token.put("validateTime", calendar.getTime());


            final JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(token);

            return asList(jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact(), foundUser.getId().toString());
        }

        throw new UsernameNotFoundException("Auth Error.");
    }
}
