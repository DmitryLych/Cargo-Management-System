package lych.trucks.domain.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_for_driver_license", referencedColumnName = "driver_license_id")
    private DriverLicense driverLicense;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_for_medical_examination", referencedColumnName = "medical_examination_id")
    private MedicalExamination medicalExamination;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_for_truck", referencedColumnName = "truck_id")
    private Truck truck;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_for_insurance_policy", referencedColumnName = "insurance_policy_id")
    private InsurancePolicy insurancePolicy;

    @Column(name = "owner_id")
    private Integer ownerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_driver", referencedColumnName = "order_id")
    private Order order;
}
