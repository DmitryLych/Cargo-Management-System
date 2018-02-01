package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Company;

import java.io.Serializable;
import java.util.List;

/**
 * Dto for {@link Company} request.
 */
@Data
@JsonTypeName(value = "company")
public class CompanyRequest implements Serializable {

    private static final long serialVersionUID = 9077411237371197620L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    @NonNull
    private String companyName;

    @JsonProperty
    @NonNull
    private String address;

    @JsonProperty
    @NonNull
    private String email;

    @JsonProperty
    private String telephoneNumber;

    @JsonProperty
    private List<DriverRequest> drivers;
}
