package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Order;

import java.io.Serializable;

/**
 * Dto for {@link Order} response.
 */
@Data
@JsonTypeName(value = "orders")
public class OrderResponse implements Serializable {

    private static final long serialVersionUID = 8098824423531359494L;

    @JsonProperty
    private Integer orderId;

    @JsonProperty
    private Double coast;

    @JsonProperty
    private String downloadAddress;

    @JsonProperty
    private String unloadingAddress;

    @JsonProperty
    @JsonInclude
    private boolean issued;

    @JsonProperty
    @JsonInclude
    private boolean completed;

    @JsonProperty
    @JsonInclude
    private boolean paid;
}
