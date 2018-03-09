package lych.trucks.domain.model;

import lombok.Data;
import lych.trucks.application.security.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Entity that describes Customer.
 */
@Entity
@Data
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 8643436539149880802L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "address")
    private String address;

    @Column(name = "customer_name", unique = true)
    private String customerName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "company_telephone_number", unique = true)
    private String companyTelephoneNumber;

    @Column(name = "mobile_telephone_number", unique = true)
    private String mobileTelephoneNumber;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Order> orders;

    @ManyToOne
    private User user;
}
