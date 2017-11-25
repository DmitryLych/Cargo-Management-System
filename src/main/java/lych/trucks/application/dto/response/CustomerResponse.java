package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Customer;

import java.io.Serializable;

/**
 * Dto for {@link Customer} response.
 */
@Data
@JsonTypeName(value = "customers")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse implements Serializable {

    private static final long serialVersionUID = 1065041209128447641L;

    @JsonProperty
    private Integer customerId;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String email;

    @JsonProperty
    private String companyTelephoneNumber;

    @JsonProperty
    private String mobileTelephoneNumber;
}
