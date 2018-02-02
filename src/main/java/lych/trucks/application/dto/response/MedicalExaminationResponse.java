package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.MedicalExamination;

import java.io.Serializable;

/**
 * Dto for {@link MedicalExamination} response.
 */
@Data
@JsonTypeName(value = "medicalExamination")
public class MedicalExaminationResponse implements Serializable {

    private static final long serialVersionUID = 2186651219474385589L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Long validate;
}
