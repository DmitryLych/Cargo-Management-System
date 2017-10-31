package lych.trucks.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@Table(name = "insurance_policies")
public class InsurancePolicy implements Persistable<Integer> {

    private static final long serialVersionUID = 1324017582459602727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_policy_id")
    private Integer id;

    @Column(name = "validate")
    private Date validate;

    @Column(name = "type_insurance_policy")
    private String type;

    @Column(name = "cost_of_payment")
    private double cost;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
