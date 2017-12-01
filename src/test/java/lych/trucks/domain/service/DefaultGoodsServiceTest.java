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

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
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

        goodsRepository.deleteAll();

        final Order order = new Order();

        orderIdContent = orderRepository.save(order).getOrderId();

        final Goods goods = new Goods();

        goods.setOwnerOrderId(orderIdContent);
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

        newId = goodsService.create(orderIdContent, goods).getGoodsId();

        assertThat(goodsRepository.findOne(newId).getName(), Is.is(content));
    }

    @Test
    public void update() {

        final String content = "new name";

        final Goods goods = goodsRepository.findOne(goodsIdContent);

        goods.setName(content);

        goodsService.update(goods);

        assertThat(goodsRepository.findOne(goodsIdContent).getName(), Is.is(content));
    }

    @Test
    public void delete() {

        goodsService.delete(goodsIdContent);

        assertThat(goodsRepository.exists(goodsIdContent), Is.is(false));
    }

    @Test
    public void fetchAll() {

        final List<Goods> goods = goodsService.fetchAll(orderIdContent);

        goods.forEach(value -> assertThat(value.getName(), Is.is(NAME_CONTENT)));
    }

    @Test
    public void fetch() {

        assertThat(goodsService.fetch(goodsIdContent).getName(), Is.is(NAME_CONTENT));
    }

    @Test
    public void fetchByType() {

        final List<Goods> goodsList = goodsService.fetchByType(TYPE_CONTENT);

        goodsList.forEach(goods -> assertThat(goods.getGoodsType(), Is.is(TYPE_CONTENT)));

    }

    @Test
    public void fetchByName() {

        final List<Goods> goodsList = goodsService.fetchByName(NAME_CONTENT);

        goodsList.forEach(goods -> assertThat(goods.getName(), Is.is(NAME_CONTENT)));
    }
}
