package lych.trucks.domain.repository;

import lych.trucks.domain.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    @Query(value = "SELECT * FROM goods WHERE owner_order_id=?1", nativeQuery = true)
    List<Goods> findByOwnerOrderId(Integer orderId);
}
