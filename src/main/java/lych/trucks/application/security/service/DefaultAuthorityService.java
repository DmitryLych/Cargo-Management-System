package lych.trucks.application.security.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.security.model.Authority;
import lych.trucks.application.security.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link AuthorityService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultAuthorityService implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority createAuthority(final Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority updateAuthority(final Authority authority) throws Exception {

        final Authority savedAuthority = Optional.ofNullable(authorityRepository.findOne(authority.getId()))
                .orElseThrow(() -> new Exception("Incorrect Authority."));

        authority.setAuthority(authority.getAuthority() == null ? savedAuthority.getAuthority()
                : authority.getAuthority());
        authority.setUserId(savedAuthority.getUserId());

        return authorityRepository.save(authority);
    }
}
