package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Trailer;

import java.io.Serializable;

/**
 * Dto for {@link Trailer} response.
 */
@Data
@JsonTypeName(value = "trailer")
public class TrailerResponse implements Serializable {

    private static final long serialVersionUID = -1104848201726940177L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String registerSign;

    @JsonProperty
    private String color;

    @JsonProperty
    private String trailerType;

    @JsonProperty
    private Double weight;

    @JsonProperty
    private Double longest;

    @JsonProperty
    private Integer volume;

    @JsonProperty
    private Double height;

    @JsonProperty
    private Long yearOfIssue;
}
