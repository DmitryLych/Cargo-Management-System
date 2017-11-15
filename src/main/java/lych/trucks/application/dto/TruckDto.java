package lych.trucks.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeName(value = "truck")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TruckDto implements Serializable {

    private static final long serialVersionUID = 4792823910131444254L;

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

    @JsonProperty
    private TrailerDto trailer;
}
