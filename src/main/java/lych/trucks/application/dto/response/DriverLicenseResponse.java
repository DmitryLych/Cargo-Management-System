package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.DriverLicense;

import java.io.Serializable;

/**
 * Dto for {@link DriverLicense} response.
 */
@Data
@JsonTypeName(value = "driverLicense")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
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
