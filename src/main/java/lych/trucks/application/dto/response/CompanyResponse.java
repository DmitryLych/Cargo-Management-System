package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Company;

import java.io.Serializable;

/**
 * Dto for {@link Company} response.
 */
@Data
@JsonTypeName(value = "company")
public class CompanyResponse implements Serializable {

    private static final long serialVersionUID = 6590986439451007101L;

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
}
