package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeName(value = "insurancePolicy")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class InsurancePolicyResponse implements Serializable {

    private static final long serialVersionUID = -420445887801321788L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private long validate;

    @JsonProperty
    private String type;

    @JsonProperty
    private double cost;
}
