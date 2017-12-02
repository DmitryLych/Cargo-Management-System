package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.MedicalExamination;

import java.io.Serializable;

/**
 * Dto for {@link MedicalExamination} request.
 */
@Data
@JsonTypeName(value = "medicalExamination")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalExaminationRequest implements Serializable {

    private static final long serialVersionUID = -8517019221360490484L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private long validate;

    @JsonProperty
    private Integer medicalExaminationFk;
}
