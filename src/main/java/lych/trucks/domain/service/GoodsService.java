package lych.trucks.domain.service;

import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;

import java.util.List;

/**
 * Service for {@link Goods} work with database.
 */
public interface GoodsService {

    /**
     * Method for create goods.
     *
     * @param orderId {@link Order} orderId.
     * @param goods   Goods goods.
     * @return created goods.
     */
    Goods create(Integer orderId, Goods goods);

    /**
     * Method for update goods.
     *
     * @param goods Goods goods.
     * @return updated goods.
     */
    Goods update(Goods goods);

    /**
     * Method for delete goods.
     *
     * @param goodsId Goods goodsId.
     * @return deleted goods.
     */
    Goods delete(Integer goodsId);

    /**
     * Method for fetch all goods.
     *
     * @param orderId {@link Order} orderId.
     * @return list of goods which found.
     */
    List<Goods> fetchAll(Integer orderId);

    /**
     * Method for fetch some goods by id.
     *
     * @param goodsId Goods goodsId.
     * @return goods which found.
     */
    Goods fetch(Integer goodsId);

    /**
     * Method for fetch goods by type.
     *
     * @param goodsType Goods goodsType.
     * @return list of goods which found.
     */
    List<Goods> fetchByType(String goodsType);

    /**
     * Method for fethc goods by name.
     *
     * @param name Goods name.
     * @return list of goods which found.
     */
    List<Goods> fetchByName(String name);
}
