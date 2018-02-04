package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link GoodsService}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultGoodsService implements GoodsService {

    private final GoodsRepository goodsRepository;

    private final OrderService orderService;

    @Override
    public Goods createGoods(final Integer orderId, final Goods goods) {

        final Order order = orderService.fetchOrder(orderId);

        goods.setOrder(order);

        return goodsRepository.save(goods);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Goods updateGoods(final Goods goods) {

        final Goods saved = goodsRepository.findOne(goods.getGoodsId());

        goods.setOrder(goods.getOrder() == null ? saved.getOrder() : goods.getOrder());
        goods.setGoodsType(goods.getGoodsType() == null ? saved.getGoodsType() : goods.getGoodsType());
        goods.setName(goods.getName() == null ? saved.getName() : goods.getName());
        goods.setVolume(goods.getVolume() == null ? saved.getVolume() : goods.getVolume());
        goods.setWeight(goods.getWeight() == null ? saved.getWeight() : goods.getWeight());

        return goodsRepository.save(goods);
    }

    @Override
    public Goods deleteGoods(final Integer goodsId) {

        final Goods goodsToDelete = goodsRepository.findOne(goodsId);

        goodsRepository.delete(goodsId);

        return goodsToDelete;
    }

    @Override
    public List<Goods> fetchAllGoods(final Integer orderId) {
        return goodsRepository.findAllByOrder(orderId);
    }

    @Override
    public Goods fetchGoods(final Integer goodsId) {
        return goodsRepository.findOne(goodsId);
    }

    @Override
    public List<Goods> fetchGoodsByType(final String goodsType) {
        return goodsRepository.findByType(goodsType);
    }

    @Override
    public List<Goods> fetchGoodsByName(final String name) {
        return goodsRepository.findByName(name);
    }
}
