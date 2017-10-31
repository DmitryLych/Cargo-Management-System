package lych.trucks.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "medical_examinations")
public class MedicalExamination implements Persistable<Integer>{

    private static final long serialVersionUID = -5670751621152583053L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_examination_id")
    private Integer id;

    @Column(name = "validate")
    private Date validate;

    @OneToOne(mappedBy = "medicalExamination")
    private Driver driver;

    @Override
    public boolean isNew() {
        return Objects.nonNull(id);
    }
}
