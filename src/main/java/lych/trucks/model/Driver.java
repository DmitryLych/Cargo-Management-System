package lych.trucks.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "drivers")
public class Driver implements Persistable<Integer> {

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
    private Date yearOfIssue;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_license_id")
    private DriverLicense driverLicense;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_examination_id")
    private MedicalExamination medicalExamination;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_policy_id")
    InsurancePolicy insurancePolicy;

    @ManyToMany(mappedBy = "drivers")
    private List<Company> company;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
