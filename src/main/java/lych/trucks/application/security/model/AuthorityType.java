package lych.trucks.application.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * Authority type.
 */
@RequiredArgsConstructor
public enum AuthorityType {

    /**
     * Admin.
     */
    ADMIN("Admin"),

    /**
     * Driver.
     */
    DRIVER("Driver"),

    /**
     * Customer.
     */
    CUSTOMER("Customer"),

    /**
     * Company lead.
     */
    COMPANY_LEAD("CompanyLead"),

    /**
     * User.
     */
    USER("User");

    @Getter
    private final String value;

    /**
     * Get all authorities.
     */
    public static final List<AuthorityType> AUTHORITIES = unmodifiableList(asList(values()));
}
