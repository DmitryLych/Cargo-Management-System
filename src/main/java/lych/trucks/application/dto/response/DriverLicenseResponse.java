package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.DriverLicense;

import java.io.Serializable;

/**
 * Dto for {@link DriverLicense} response.
 */
@Data
@JsonTypeName(value = "driverLicense")
public class DriverLicenseResponse implements Serializable {

    private static final long serialVersionUID = -5303965589053079379L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String category;

    @JsonProperty
    private Long validate;

    @JsonProperty
    private String specialNotes;
}
