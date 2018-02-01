package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.InsurancePolicy;

import java.io.Serializable;

/**
 * Dto for {@link InsurancePolicy} response.
 */
@Data
@JsonTypeName(value = "insurancePolicy")
public class InsurancePolicyResponse implements Serializable {

    private static final long serialVersionUID = -420445887801321788L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private Long validate;

    @JsonProperty
    private String type;

    @JsonProperty
    private Double cost;
}
