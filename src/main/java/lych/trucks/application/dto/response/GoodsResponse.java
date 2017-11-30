package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lych.trucks.domain.model.Goods;

import java.io.Serializable;

/**
 * Dto for {@link Goods} response.
 */
@Data
@JsonTypeName(value = "goods")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsResponse implements Serializable {

    private static final long serialVersionUID = -4978552629010266835L;

    @JsonProperty
    private Integer goodsId;

    @JsonProperty
    private String name;

    @JsonProperty
    private double weight;

    @JsonProperty
    private double volume;

    @JsonProperty
    private String goodsType;
}
