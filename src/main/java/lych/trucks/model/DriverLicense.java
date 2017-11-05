package lych.trucks.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "driver_licenses")
public class DriverLicense implements Persistable<Integer> {

    private static final long serialVersionUID = -6976625888749317669L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_license_id")
    private Integer id;

    @Column(name = "categories")
    private String category;

    @Column(name = "validate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validate;

    @Column(name = "special_notes")
    private String specialNotes;

    @OneToOne(mappedBy = "driverLicense")
    private Driver driver;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
