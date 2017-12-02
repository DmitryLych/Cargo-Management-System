package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Order;

import java.io.Serializable;
import java.util.List;

/**
 * Dto for {@link Order} request.
 */
@Data
@JsonTypeName(value = "orders")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 5176310672351512435L;

    @JsonProperty
    private Integer orderId;

    @JsonProperty
    private double coast;

    @JsonProperty
    private String downloadAddress;

    @JsonProperty
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
