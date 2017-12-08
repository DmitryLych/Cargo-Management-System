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
import javax.persistence.ManyToOne;
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
    private Double coast;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Goods> goods;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
