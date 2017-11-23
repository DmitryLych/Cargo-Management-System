package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.application.dto.response.TrailerResponse;

import java.io.Serializable;

@Data
@JsonTypeName(value = "truck")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TruckRequest implements Serializable {

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
    private Integer ownerIdForTruck;

    @JsonProperty
    private TrailerResponse trailer;
}
