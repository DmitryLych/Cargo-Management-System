package lych.trucks.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.NonNull;
import lych.trucks.domain.model.Goods;

import java.io.Serializable;

/**
 * Dto for {@link Goods} request.
 */
@Data
@JsonTypeName(value = "goods")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsRequest implements Serializable {

    private static final long serialVersionUID = 8652353754650930082L;

    @JsonProperty
    private Integer goodsId;

    @JsonProperty
    @NonNull
    private String name;

    @JsonProperty
    @NonNull
    private Double weight;

    @JsonProperty
    @NonNull
    private Double volume;

    @JsonProperty
    @NonNull
    private String goodsType;

    @JsonProperty
    private OrderRequest order;
}
