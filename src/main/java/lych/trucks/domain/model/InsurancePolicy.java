package lych.trucks.domain.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity that describes Insurance policies.
 */
@Entity
@Data
@Table(name = "insurance_policies")
public class InsurancePolicy implements Serializable {

    private static final long serialVersionUID = 1324017582459602727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_policy_id")
    private Integer id;

    @Column(name = "validate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validate;

    @Column(name = "type_insurance_policy")
    private String type;

    @Column(name = "cost_of_payment")
    private Double cost;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
