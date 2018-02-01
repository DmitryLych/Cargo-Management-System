package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Trailer;

import java.io.Serializable;

/**
 * Dto for {@link Trailer} request.
 */
@Data
@JsonTypeName(value = "trailer")
public class TrailerRequest implements Serializable {

    private static final long serialVersionUID = 5654559859909333441L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    @NonNull
    private String registerSign;

    @JsonProperty
    @NonNull
    private String color;

    @JsonProperty
    @NonNull
    private String trailerType;

    @JsonProperty
    @NonNull
    private Double weight;

    @JsonProperty
    @NonNull
    private Double longest;

    @JsonProperty
    @NonNull
    private Integer volume;

    @JsonProperty
    @NonNull
    private Double height;

    @JsonProperty
    @NonNull
    private Long yearOfIssue;

    @JsonProperty
    private Integer trailerFk;
}
