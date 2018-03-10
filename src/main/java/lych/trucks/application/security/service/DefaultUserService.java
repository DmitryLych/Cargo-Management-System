package lych.trucks.application.security.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.model.Authority;
import lych.trucks.application.security.model.AuthorityType;
import lych.trucks.application.security.model.User;
import lych.trucks.application.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static lych.trucks.application.security.model.AuthorityType.ADMIN;
import static lych.trucks.application.security.model.AuthorityType.COMPANY_LEAD;
import static lych.trucks.application.security.model.AuthorityType.CUSTOMER;
import static lych.trucks.application.security.model.AuthorityType.DRIVER;

/**
 * Implementation of {@link UserService}.
 */
@SuppressWarnings("PMD")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    private final AuthorityService authorityService;

    private static final List<AuthorityType> AUTHORITY_TYPES = asList(ADMIN, DRIVER,
            CUSTOMER,
            COMPANY_LEAD);

    @Override
    public User createUser(final User user) {
        validateAuthority(user.getAuthorities());

        final Authority authority = user.getAuthorities().get(0);

        authorityService.createAuthority(authority);

        user.setAuthorities(singletonList(authority));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(final User user) {

        final User fetched = Optional.ofNullable(userRepository.findOne(user.getId()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        user.setUsername(fetched.getUsername());
        user.setAuthorities(fetched.getAuthorities());
        user.setPassword(user.getPassword() == null ? fetched.getPassword() : user.getPassword());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(final Integer userId) {
        userRepository.delete(userId);
    }

    @Override
    public User getUserByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public User fetchUser(final Integer userId) {
        return Optional.ofNullable(userRepository.findOne(userId))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    private static void validateAuthority(final List<Authority> authorities) {

        if (authorities.stream()
                .filter(authority -> AUTHORITY_TYPES.contains(AuthorityType.valueOf(authority.getAuthority().toUpperCase())))
                .collect(toList()).size() < authorities.size()) {

            throw new IllegalArgumentException("Illegal Authority");
        }
    }
}
