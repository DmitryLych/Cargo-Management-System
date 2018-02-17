package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        validateGoods(goods);

        final Order order = orderService.fetchOrder(orderId);

        goods.setOrder(order);

        return goodsRepository.save(goods);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Goods updateGoods(final Goods goods) {
        validateGoods(goods);

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

        final Goods goodsToDelete = fetchGoods(goodsId);

        goodsRepository.delete(goodsId);

        return goodsToDelete;
    }

    @Override
    public List<Goods> fetchAllGoods(final Integer orderId) {

        final List<Goods> goods = goodsRepository.findAllByOrder(orderId);

        if (goods == null || goods.isEmpty()) {
            throw new IllegalArgumentException("Goods not found."
                    + " Goods in Order with this Id: '" + orderId + "' not exists.");
        }
        return goods;
    }

    @Override
    public Goods fetchGoods(final Integer goodsId) {
        return Optional.ofNullable(goodsRepository.findOne(goodsId))
                .orElseThrow(() -> new IllegalArgumentException("Goods not found."
                        + " Goods with this Id: '" + goodsId + "' not exists."));
    }

    @Override
    public List<Goods> fetchGoodsByType(final String goodsType) {

        final List<Goods> goods = goodsRepository.findByType(goodsType);

        if (goods == null || goods.isEmpty()) {
            throw new IllegalArgumentException("Goods not found. "
                    + "Goods with this type: '" + goodsType + "' not exists.");
        }
        return goods;
    }

    @Override
    public List<Goods> fetchGoodsByName(final String name) {

        final List<Goods> goods = goodsRepository.findByName(name);

        if (goods == null || goods.isEmpty()) {
            throw new IllegalArgumentException("Goods not found. "
                    + "Goods with this name: '" + name + "' not exists.");
        }

        return goods;
    }

    private static void validateGoods(final Goods goods) {

        if (goods == null) {
            throw new IllegalArgumentException("Goods can`t be null.");
        }
    }
}
