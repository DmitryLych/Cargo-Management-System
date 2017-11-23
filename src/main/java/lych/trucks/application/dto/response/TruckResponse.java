package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeName(value = "truck")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TruckResponse implements Serializable {

    private static final long serialVersionUID = 3497041179490923550L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String registerSign;

    @JsonProperty
    private String bodyNumber;

    @JsonProperty
    private float weight;

    @JsonProperty
    private float length;

    @JsonProperty
    private float height;

    @JsonProperty
    private String color;

    @JsonProperty
    private long yearOfIssue;
}
