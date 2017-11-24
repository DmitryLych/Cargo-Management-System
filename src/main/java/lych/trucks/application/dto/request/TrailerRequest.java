package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Trailer;

import java.io.Serializable;

/**
 * Dto for {@link Trailer} request.
 */
@Data
@JsonTypeName(value = "trailer")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrailerRequest implements Serializable {

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

    @JsonProperty
    private Integer ownerIdForTrailer;
}
