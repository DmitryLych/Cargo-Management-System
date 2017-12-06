package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.GoodsRequest;
import lych.trucks.application.dto.response.GoodsResponse;
import lych.trucks.domain.model.Goods;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.service.GoodsService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest controller for {@link Goods}.
 */
@RestController
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
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}/goods", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer orderId, @RequestBody final GoodsRequest request) {

        final Goods goodsToCreate = dozerBeanMapper.map(request, Goods.class);

        final Goods goodsToResponse = goodsService.create(orderId, goodsToCreate);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for updated goods.
     *
     * @param request {@link GoodsRequest} request.
     * @return {@link GoodsResponse} response mapped from updated goods.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}/goods", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final GoodsRequest request) {

        final Goods goodsToUpdate = dozerBeanMapper.map(request, Goods.class);

        final Goods goodsToResponse = goodsService.update(goodsToUpdate);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete some goods.
     *
     * @param goodsId {@link Goods} goodsId.
     * @return {@link GoodsResponse} response mapped from deleted goods.
     */
    @RequestMapping(value = "customers/{customerId}/orders/{orderId}/goods/{goodsId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer goodsId) {

        final Goods goodsToResponse = goodsService.delete(goodsId);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch all goods which contain in some order.
     *
     * @param orderId {@link Order} orderId.
     * @return list of {@link GoodsResponse} response mapped from list of orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}/goods", method = RequestMethod.GET)
    public ResponseEntity fetchAll(@PathVariable final Integer orderId) {

        final List<Goods> goodsToResponse = goodsService.fetchAll(orderId);

        final List<GoodsResponse> response = new ArrayList<>();

        goodsToResponse.forEach(goods -> response.add(dozerBeanMapper.map(goods, GoodsResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch some goods.
     *
     * @param goodsId {@link Goods} goodsId.
     * @return {@link GoodsResponse} response mapped from goods which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}/goods/{goodsId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer goodsId) {

        final Goods goodsToResponse = goodsService.fetch(goodsId);

        final GoodsResponse response = dozerBeanMapper.map(goodsToResponse, GoodsResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch goods by type.
     *
     * @param goodsType {@link Goods} goodsType.
     * @return list of {@link GoodsResponse} response mapped from list of orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}/goods/type/{goodsType}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByType(@PathVariable final String goodsType) {

        final List<Goods> goodsToResponse = goodsService.fetchByType(goodsType);

        final List<GoodsResponse> response = new ArrayList<>();

        goodsToResponse.forEach(goods -> response.add(dozerBeanMapper.map(goods, GoodsResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch goods by name.
     *
     * @param goodsName {@link Goods} name.
     * @return list of {@link GoodsResponse} response mapped from list of orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}/goods/name/{goodsName}",
            method = RequestMethod.GET)
    public ResponseEntity fetchByName(@PathVariable final String goodsName) {

        final List<Goods> goodsToResponse = goodsService.fetchByName(goodsName);

        final List<GoodsResponse> response = new ArrayList<>();

        goodsToResponse.forEach(goods -> response.add(dozerBeanMapper.map(goods, GoodsResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
