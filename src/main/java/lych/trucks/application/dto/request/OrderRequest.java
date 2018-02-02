package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Dto for {@link Order} request.
 */
@Data
@JsonTypeName(value = "orders")
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 5176310672351512435L;

    @JsonProperty
    private Integer orderId;

    @JsonProperty
    @NonNull
    private Double coast;

    @JsonProperty
    @NonNull
    private String downloadAddress;

    @JsonProperty
    @NonNull
    private String unloadingAddress;

    @JsonProperty
    private boolean issued;

    @JsonProperty
    private boolean completed;

    @JsonProperty
    private boolean paid;

    @JsonProperty
    private CustomerRequest customer;

    @JsonProperty
    private List<GoodsRequest> goods;

    @JsonProperty
    private DriverRequest driver;
}
