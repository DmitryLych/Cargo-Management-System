package lych.trucks.domain.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Entity that describes Company.
 */
@Entity
@Data
@Table(name = "companies")
public class Company implements Serializable {

    private static final long serialVersionUID = -2249584128987940075L;

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "company_id")
    private List<Driver> drivers;
}
