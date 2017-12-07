package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
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
    private String lastName;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private long yearOfIssue;

    @JsonProperty
    private String address;

    @JsonProperty
    private String telephoneNumber;

    @JsonProperty
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
