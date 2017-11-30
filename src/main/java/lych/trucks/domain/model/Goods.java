package lych.trucks.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity that describes Goods.
 */
@Entity
@Data
@Table(name = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1134983672686219570L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Integer goodsId;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private double weight;

    @Column(name = "volume")
    private double volume;

    @Column(name = "goods_type")
    private String goodsType;

    @Column(name = "owner_order_id")
    private Integer ownerOrderId;
}
