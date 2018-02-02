package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Customer;

import java.io.Serializable;
import java.util.List;

/**
 * Dto for {@link Customer} request.
 */
@Data
@JsonTypeName(value = "customers")
public class CustomerRequest implements Serializable {

    private static final long serialVersionUID = -9050131867256691474L;

    @JsonProperty
    private Integer customerId;

    @JsonProperty
    @NonNull
    private String address;

    @JsonProperty
    @NonNull
    private String customerName;

    @JsonProperty
    @NonNull
    private String email;

    @JsonProperty
    private String companyTelephoneNumber;

    @JsonProperty
    private String mobileTelephoneNumber;

    @JsonProperty
    private List<OrderRequest> orders;
}
