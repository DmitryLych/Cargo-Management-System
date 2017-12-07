package lych.trucks.domain.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Entity that describes Driver.
 */
@Entity
@Data
@Table(name = "drivers")
public class Driver implements Serializable {

    private static final long serialVersionUID = -2110373679863665410L;

    @Id
    @Column(name = "driver_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "year_of_issue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yearOfIssue;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "status")
    private boolean status;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "driver_license_fk", referencedColumnName = "driver_license_id")
    private DriverLicense driverLicense;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "medical_examination_fk", referencedColumnName = "medical_examination_id")
    private MedicalExamination medicalExamination;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "truck_fk", referencedColumnName = "truck_id")
    private Truck truck;

    @OneToMany(mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<InsurancePolicy> insurancePolicies;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Order> order;
}
