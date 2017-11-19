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
 * Entity that describes Medical examinations.
 */
@Data
@Entity
@Table(name = "medical_examinations")
public class MedicalExamination implements Serializable {

    private static final long serialVersionUID = -5670751621152583053L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_examination_id")
    private Integer id;

    @Column(name = "validate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validate;

    @Column(name = "owner_id_for_medical_examination")
    private Integer ownerIdForMedicalExamination;
}
