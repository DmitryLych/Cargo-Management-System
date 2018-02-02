package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Truck;

import java.io.Serializable;

/**
 * Dto for {@link Truck} response.
 */
@Data
@JsonTypeName(value = "truck")
public class TruckResponse implements Serializable {

    private static final long serialVersionUID = 3497041179490923550L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String registerSign;

    @JsonProperty
    private String bodyNumber;

    @JsonProperty
    private Double weight;

    @JsonProperty
    private String color;

    @JsonProperty
    private Long yearOfIssue;
}
