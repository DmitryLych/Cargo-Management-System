package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.GoodsRequest;
import lych.trucks.application.dto.response.GoodsResponse;
import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.service.GoodsService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link Goods}.
 */
@RestController
@RequestMapping("/cargo/v1/{userId}/customers/{customerId}/orders/{orderId}/goods")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GoodsController {

    private final DozerBeanMapper dozerBeanMapper;

    private final GoodsService goodsService;

    /**
     * Method for create goods.
     *
     * @param orderId {@link Order} orderId.
     * @param request {@link GoodsRequest} request.
     * @return {@link GoodsResponse} response mapped from created goods.
     */
    @PostMapping
    public ResponseEntity createGoods(@PathVariable final Integer orderId, @RequestBody final GoodsRequest request) {
        final Goods goodsToCreate = dozerBeanMapper.map(request, Goods.class);
        final Goods goodsToResponse = goodsService.createGoods(orderId, goodsToCreate);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for updated goods.
     *
     * @param request {@link GoodsRequest} request.
     * @return {@link GoodsResponse} response mapped from updated goods.
     */
    @PutMapping
    public ResponseEntity updateGoods(@RequestBody final GoodsRequest request) {
        final Goods goodsToUpdate = dozerBeanMapper.map(request, Goods.class);
        final Goods goodsToResponse = goodsService.updateGoods(goodsToUpdate);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for delete some goods.
     *
     * @param goodsId {@link Goods} goodsId.
     * @return {@link GoodsResponse} response mapped from deleted goods.
     */
    @DeleteMapping(path = "/{goodsId}")
    public ResponseEntity deleteGoods(@PathVariable final Integer goodsId) {
        final Goods goodsToResponse = goodsService.deleteGoods(goodsId);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch all goods which contain in some order.
     *
     * @param orderId {@link Order} orderId.
     * @return list of {@link GoodsResponse} response mapped from list of orders which found.
     */
    @GetMapping
    public ResponseEntity fetchAllGoods(@PathVariable final Integer orderId) {
        final List<Goods> goodsToResponse = goodsService.fetchAllGoods(orderId);

        final List<GoodsResponse> response = goodsToResponse.stream()
                .map(g -> dozerBeanMapper.map(g, GoodsResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch some goods.
     *
     * @param goodsId {@link Goods} goodsId.
     * @return {@link GoodsResponse} response mapped from goods which found.
     */
    @GetMapping(path = "/{goodsId}")
    public ResponseEntity fetchGoods(@PathVariable final Integer goodsId) {
        final Goods goodsToResponse = goodsService.fetchGoods(goodsId);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch goods by type.
     *
     * @param goodsType {@link Goods} goodsType.
     * @return list of {@link GoodsResponse} response mapped from list of orders which found.
     */
    @GetMapping(path = "/type/{goodsType}")
    public ResponseEntity fetchGoodsByType(@PathVariable final String goodsType) {
        final List<Goods> goodsToResponse = goodsService.fetchGoodsByType(goodsType);

        final List<GoodsResponse> response = goodsToResponse.stream()
                .map(g -> dozerBeanMapper.map(g, GoodsResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Method for fetch goods by name.
     *
     * @param goodsName {@link Goods} name.
     * @return list of {@link GoodsResponse} response mapped from list of orders which found.
     */
    @GetMapping(path = "/name/{goodsName}")
    public ResponseEntity fetchByName(@PathVariable final String goodsName) {
        final List<Goods> goodsToResponse = goodsService.fetchGoodsByName(goodsName);

        final List<GoodsResponse> response = goodsToResponse.stream()
                .map(g -> dozerBeanMapper.map(g, GoodsResponse.class))
                .collect(toList());
        return ResponseEntity.ok(response);
    }
}
