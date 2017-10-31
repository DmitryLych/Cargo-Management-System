package lych.trucks.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "trucks")
public class Truck implements Persistable<Integer> {

    private static final long serialVersionUID = 656952043415287092L;

    @Id
    @Column(name = "truck_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "register_sign")
    private String registerSign;

    @Column(name = "body_number")
    private String bodyNumber;

    @Column(name = "weight")
    private float weight;

    @Column(name = "length")
    private float length;

    @Column(name = "height")
    private float height;

    @Column(name = "color")
    private String color;

    @Column(name = "year_of_issue")
    private Date yearOfIssue;

    @OneToOne(mappedBy = "truck")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trailer_id")
    Trailer trailer;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
