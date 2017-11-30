package lych.trucks.domain.repository;

import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interface for {@link Goods} work with database.
 */
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    /**
     * Method for find goods by {@link Order} orderId.
     *
     * @param orderId Order orderId.
     * @return list of found goods.
     */
    @Query(value = "SELECT * FROM goods WHERE owner_order_id=?1", nativeQuery = true)
    List<Goods> findByOwnerOrderId(Integer orderId);

    /**
     * Method for find goods by type.
     *
     * @param goodsType Goods goodsType.
     * @return list of found goods.
     */
    @Query(value = "SELECT * FROM goods WHERE goods_type=?1", nativeQuery = true)
    List<Goods> findByType(String goodsType);

    /**
     * Method for find goods by name.
     *
     * @param name Goods name.
     * @return list of found goods.
     */
    @Query(value = "SELECT * FROM goods WHERE name=?1", nativeQuery = true)
    List<Goods> findByName(String name);
}
