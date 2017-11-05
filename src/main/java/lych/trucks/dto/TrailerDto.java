package lych.trucks.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeName(value = "trailer")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrailerDto implements Serializable{

    private static final long serialVersionUID = 5654559859909333441L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String registerSign;

    @JsonProperty
    private float weight;

    @JsonProperty
    private float length;

    @JsonProperty
    private float height;

    @JsonProperty
    private long yearOfIssue;
}
