package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Company;

import java.io.Serializable;
import java.util.List;

/**
 * Dto for {@link Company} request.
 */
@Data
@JsonTypeName(value = "company")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyRequest implements Serializable {

    private static final long serialVersionUID = 9077411237371197620L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String email;

    @JsonProperty
    private String telephoneNumber;

    @JsonProperty
    private List<DriverRequest> drivers;
}
