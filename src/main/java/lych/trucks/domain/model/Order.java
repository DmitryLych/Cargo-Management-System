package lych.trucks.domain.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Entity that describes Order.
 */
@Entity
@Data
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -6111686060167887727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "coast")
    private double coast;

    @Column(name = "download_address")
    private String downloadAddress;

    @Column(name = "unloading_address")
    private String unloadingAddress;

    @Column(name = "issued")
    private boolean issued;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "owner_customer_id")
    private Integer ownerCustomerId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_order_id", referencedColumnName = "order_id")
    private List<Goods> goods;

    @Column(name = "owner_id_driver")
    private Integer ownerIdDriver;
}
