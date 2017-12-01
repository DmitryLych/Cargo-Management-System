package lych.trucks.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.model.Goods;
import lych.trucks.domain.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link GoodsService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultGoodsService implements GoodsService {

    private final GoodsRepository goodsRepository;

    @Override
    public Goods create(final Integer orderId, final Goods goods) {

        log.info("Goods created.");

        goods.setOwnerOrderId(orderId);

        return goodsRepository.save(goods);
    }

    @Override
    @SuppressWarnings("PMD.NPathComplexity")
    public Goods update(final Goods goods) {

        log.info("Goods updated.");

        final Goods saved = goodsRepository.findOne(goods.getGoodsId());

        goods.setOwnerOrderId(goods.getOwnerOrderId() == null ? saved.getOwnerOrderId() : goods.getOwnerOrderId());
        goods.setGoodsType(goods.getGoodsType() == null ? saved.getGoodsType() : goods.getGoodsType());
        goods.setName(goods.getName() == null ? saved.getName() : goods.getName());
        goods.setVolume(goods.getVolume() == 0 ? saved.getVolume() : goods.getVolume());
        goods.setWeight(goods.getWeight() == 0 ? saved.getWeight() : goods.getWeight());

        return goodsRepository.save(goods);
    }

    @Override
    public Goods delete(final Integer goodsId) {

        log.info("Goods deleted.");

        final Goods goodsToDelete = goodsRepository.findOne(goodsId);

        goodsRepository.delete(goodsId);

        return goodsToDelete;
    }

    @Override
    public List<Goods> fetchAll(final Integer orderId) {

        log.info("All goods found.");

        return goodsRepository.findByOwnerOrderId(orderId);
    }

    @Override
    public Goods fetch(final Integer goodsId) {

        log.info("Goods found.");

        return goodsRepository.findOne(goodsId);
    }

    @Override
    public List<Goods> fetchByType(final String goodsType) {

        log.info("Goods fetch by type.");

        return goodsRepository.findByType(goodsType);
    }

    @Override
    public List<Goods> fetchByName(final String name) {

        log.info("Goods fetch by name.");

        return goodsRepository.findByName(name);
    }
}
