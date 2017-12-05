package lych.trucks.domain.repository;

import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoodsRepositoryTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Integer orderIdContent;

    private static final String TYPE_CONTENT = "type";

    private static final String NAME_CONTENT = "name";

    @Before
    public void setUp() {

        final Order order = orderRepository.save(new Order());

        orderIdContent = order.getOrderId();

        final Goods goods = new Goods();

        goods.setOrder(order);
        goods.setName(NAME_CONTENT);
        goods.setGoodsType(TYPE_CONTENT);

        goodsRepository.save(goods);
    }

    @Test
    public void findAllByOrder() {

        final List<Goods> foundGoods = goodsRepository.findAllByOrder(orderIdContent);

        foundGoods.forEach(goods -> assertThat(goods.getOrder().getOrderId(), Is.is(orderIdContent)));
    }

    @Test
    public void findByType() {

        final List<Goods> foundGoods = goodsRepository.findByType(TYPE_CONTENT);

        foundGoods.forEach(goods -> assertThat(goods.getGoodsType(), Is.is(TYPE_CONTENT)));
    }

    @Test
    public void findByName() {

        final List<Goods> foundGoods = goodsRepository.findByName(NAME_CONTENT);

        foundGoods.forEach(goods -> assertThat(goods.getName(), Is.is(NAME_CONTENT)));
    }

}
