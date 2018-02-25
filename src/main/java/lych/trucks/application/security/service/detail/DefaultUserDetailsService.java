package lych.trucks.application.security.service.detail;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link UserDetailsService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        return Optional.ofNullable(userService.getUserByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
