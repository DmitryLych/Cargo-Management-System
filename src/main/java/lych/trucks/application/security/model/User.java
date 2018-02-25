package lych.trucks.application.security.model;

import lombok.Data;
import lych.trucks.domain.model.Company;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity describes user.
 */
@Entity
@Data
@Table(name = "users")
@SuppressWarnings("PMD")
public class User implements UserDetails {

    private static final long serialVersionUID = 7633309585425251364L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Authority> authorities;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "company_fk", referencedColumnName = "company_id")
    private Company company;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "customer_fk", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "driver_fk", referencedColumnName = "driver_id")
    private Driver driver;
}
