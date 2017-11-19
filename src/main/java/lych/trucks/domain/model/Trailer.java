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
 * Entity that describes Trailer.
 */
@Entity
@Data
@Table(name = "trailers")
public class Trailer implements Serializable {

    private static final long serialVersionUID = -209382986962384167L;

    @Id
    @Column(name = "trailer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "register_sign")
    private String registerSign;

    @Column(name = "weight")
    private float weight;

    @Column(name = "length")
    private float length;

    @Column(name = "height")
    private float height;

    @Column(name = "year_of_issue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date yearOfIssue;

    @Column(name = "owner_id_for_trailer")
    private Integer ownerIdForTrailer;
}
