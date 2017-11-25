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
 * Entity that describes Truck.
 */
@Entity
@Data
@Table(name = "trucks")
public class Truck implements Serializable {

    private static final long serialVersionUID = 656952043415287092L;

    @Id
    @Column(name = "truck_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "register_sign", unique = true)
    private String registerSign;

    @Column(name = "body_number", unique = true)
    private String bodyNumber;

    @Column(name = "weight")
    private double weight;

    @Column(name = "color")
    private String color;

    @Column(name = "year_of_issue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yearOfIssue;

    @Column(name = "owner_id_for_truck")
    private Integer ownerIdForTruck;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_for_trailer", referencedColumnName = "trailer_id")
    private Trailer trailer;
}
