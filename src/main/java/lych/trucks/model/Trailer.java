package lych.trucks.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "trailers")
public class Trailer implements Persistable<Integer>{

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

    @OneToOne(mappedBy = "trailer")
    private Truck truck;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
