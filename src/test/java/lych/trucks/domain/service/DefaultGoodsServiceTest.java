package lych.trucks.domain.service;

import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.repository.GoodsRepository;
import lych.trucks.domain.repository.OrderRepository;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DefaultGoodsServiceTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Integer orderIdContent;

    private Integer goodsIdContent;

    private static final String TYPE_CONTENT = "type";

    private static final String NAME_CONTENT = "name";

    @Before
    public void setUp() {

        final Order order = new Order();

        orderIdContent = orderRepository.save(order).getOrderId();

        final Goods goods = new Goods();

        goods.setOrder(order);
        goods.setGoodsType(TYPE_CONTENT);
        goods.setName(NAME_CONTENT);

        goodsIdContent = goodsRepository.save(goods).getGoodsId();
    }

    @Test
    public void create() {

        final String content = "new content";

        final Goods goods = new Goods();

        goods.setName(content);

        final Integer newId;

        newId = goodsService.createGoods(orderIdContent, goods).getGoodsId();

        assertThat(goodsRepository.findOne(newId).getName(), Is.is(content));
    }

    @Test
    public void update() {

        final String content = "new name";

        final Goods goods = goodsRepository.findOne(goodsIdContent);

        goods.setName(content);

        goodsService.updateGoods(goods);

        assertThat(goodsRepository.findOne(goodsIdContent).getName(), Is.is(content));
    }

    @Test
    public void delete() {

        goodsService.deleteGoods(goodsIdContent);

        assertThat(goodsRepository.exists(goodsIdContent), Is.is(false));
    }

    @Test
    public void fetchAll() {

        final List<Goods> goods = goodsService.fetchAllGoods(orderIdContent);

        goods.forEach(value -> assertThat(value.getName(), Is.is(NAME_CONTENT)));
    }

    @Test
    public void fetch() {

        assertThat(goodsService.fetchGoods(goodsIdContent).getName(), Is.is(NAME_CONTENT));
    }

    @Test
    public void fetchByType() {

        final List<Goods> goodsList = goodsService.fetchGoodsByType(TYPE_CONTENT);

        goodsList.forEach(goods -> assertThat(goods.getGoodsType(), Is.is(TYPE_CONTENT)));

    }

    @Test
    public void fetchByName() {

        final List<Goods> goodsList = goodsService.fetchGoodsByName(NAME_CONTENT);

        goodsList.forEach(goods -> assertThat(goods.getName(), Is.is(NAME_CONTENT)));
    }
}
