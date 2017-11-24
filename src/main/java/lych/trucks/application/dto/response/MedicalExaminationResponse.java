package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.MedicalExamination;

import java.io.Serializable;

/**
 * Dto for {@link MedicalExamination} response.
 */
@Data
@JsonTypeName(value = "medicalExamination")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MedicalExaminationResponse implements Serializable {

    private static final long serialVersionUID = 2186651219474385589L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private long validate;
}
