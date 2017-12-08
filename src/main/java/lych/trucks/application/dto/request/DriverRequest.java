package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Driver;

import java.io.Serializable;
import java.util.List;

/**
 * Dto for {@link Driver} request.
 */
@Data
@JsonTypeName(value = "driver")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverRequest implements Serializable {

    private static final long serialVersionUID = -5936038318132432576L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    @NonNull
    private String lastName;

    @JsonProperty
    @NonNull
    private String firstName;

    @JsonProperty
    @NonNull
    private Long yearOfIssue;

    @JsonProperty
    private String address;

    @JsonProperty
    @NonNull
    private String telephoneNumber;

    @JsonProperty
    @NonNull
    private String email;

    @JsonProperty
    private boolean status;

    @JsonProperty
    private DriverLicenseRequest driverLicense;

    @JsonProperty
    private MedicalExaminationRequest medicalExamination;

    @JsonProperty
    private TruckRequest truck;

    @JsonProperty
    private List<InsurancePolicyRequest> insurancePolicies;

    @JsonProperty
    private CompanyRequest company;

    @JsonProperty
    private List<OrderRequest> order;
}
