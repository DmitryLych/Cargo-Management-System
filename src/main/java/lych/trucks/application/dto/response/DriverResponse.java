package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.application.dto.request.CompanyRequest;
import lych.trucks.application.dto.request.DriverLicenseRequest;
import lych.trucks.application.dto.request.InsurancePolicyRequest;
import lych.trucks.application.dto.request.MedicalExaminationRequest;
import lych.trucks.application.dto.request.TruckRequest;

import java.io.Serializable;

@Data
@JsonTypeName(value = "driver")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverResponse implements Serializable{

    private static final long serialVersionUID = -97860262938426129L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private long yearOfIssue;

    @JsonProperty
    private String address;

    @JsonProperty
    private MedicalExaminationRequest medicalExamination;

    @JsonProperty
    private DriverLicenseRequest driverLicense;

    @JsonProperty
    private TruckRequest truck;

    @JsonProperty
    private InsurancePolicyRequest insurancePolicy;
}
