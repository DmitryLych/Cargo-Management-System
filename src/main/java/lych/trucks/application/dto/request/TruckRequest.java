package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Truck;

import java.io.Serializable;

/**
 * Dto for {@link Truck} request.
 */
@Data
@JsonTypeName(value = "truck")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TruckRequest implements Serializable {

    private static final long serialVersionUID = 4792823910131444254L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    @NonNull
    private String registerSign;

    @JsonProperty
    @NonNull
    private String bodyNumber;

    @JsonProperty
    @NonNull
    private Double weight;

    @JsonProperty
    @NonNull
    private String color;

    @JsonProperty
    @NonNull
    private Long yearOfIssue;

    @JsonProperty
    private Integer truckFk;

    @JsonProperty
    private TrailerRequest trailer;
}
