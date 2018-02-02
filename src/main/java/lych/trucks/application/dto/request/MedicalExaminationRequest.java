package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.MedicalExamination;

import java.io.Serializable;

/**
 * Dto for {@link MedicalExamination} request.
 */
@Data
@JsonTypeName(value = "medicalExamination")
public class MedicalExaminationRequest implements Serializable {

    private static final long serialVersionUID = -8517019221360490484L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    @NonNull
    private Long validate;

    @JsonProperty
    private Integer medicalExaminationFk;
}
