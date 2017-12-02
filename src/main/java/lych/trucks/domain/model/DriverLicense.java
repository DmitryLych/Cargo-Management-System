package lych.trucks.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity that describes Driver license.
 */
@Data
@Entity
@Table(name = "driver_licenses")
public class DriverLicense implements Serializable {

    private static final long serialVersionUID = -6976625888749317669L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_license_id")
    private Integer id;

    @Column(name = "categories")
    private String category;

    @Column(name = "validate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validate;

    @Column(name = "special_notes")
    private String specialNotes;

    @Column(name = "driver_license_fk")
    private Integer driverLicenseFk;
}
