package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Driver;

import java.io.Serializable;

/**
 * Dto for {@link Driver} response.
 */
@Data
@JsonTypeName(value = "driver")
public class DriverResponse implements Serializable {

    private static final long serialVersionUID = -97860262938426129L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private Long yearOfIssue;

    @JsonProperty
    private String address;

    @JsonProperty
    private String telephoneNumber;

    @JsonProperty
    private String email;

    @JsonProperty
    private boolean status;
}
