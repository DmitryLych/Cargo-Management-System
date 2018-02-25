package lych.trucks.application.security.service;

import lych.trucks.application.security.model.Authority;

/**
 * Service for {@link Authority}.
 */
public interface AuthorityService {

    /**
     * Method for create authority.
     *
     * @param authority a authority.
     * @return a created authority.
     */
    Authority createAuthority(Authority authority);

    /**
     * Method for update authority.
     *
     * @param authority a authority.
     * @return a created authority.
     * @throws Exception a exception.
     */
    Authority updateAuthority(Authority authority) throws Exception;
}
