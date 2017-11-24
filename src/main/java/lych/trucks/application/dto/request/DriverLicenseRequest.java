package lych.trucks.application.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.DriverLicense;

import java.io.Serializable;

/**
 * Dto for {@link DriverLicense} request.
 */
@Data
@JsonTypeName(value = "driverLicense")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverLicenseRequest implements Serializable {

    private static final long serialVersionUID = 2416200654875155130L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String category;

    @JsonProperty
    private long validate;

    @JsonProperty
    private String specialNotes;

    @JsonProperty
    private Integer ownerIdForDriverLicense;
}
