package lych.trucks.application.controller;

import lombok.RequiredArgsConstructor;
import lych.trucks.application.dto.request.OrderRequest;
import lych.trucks.application.dto.response.OrderResponse;
import lych.trucks.domain.model.Customer;
import lych.trucks.domain.model.Driver;
import lych.trucks.domain.model.Order;
import lych.trucks.domain.service.OrderService;
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
 * Rest controller for {@link Order}.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    private final DozerBeanMapper dozerBeanMapper;

    /**
     * Method for create order.
     *
     * @param customerId {@link Customer} customerId.
     * @param request    {@link OrderRequest} request.
     * @return {@link OrderResponse} response mapped from created order.
     */
    @RequestMapping(value = "/customers/{customerId}/orders", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable final Integer customerId, @RequestBody final OrderRequest request) {

        final Order orderToCreate = dozerBeanMapper.map(request, Order.class);

        final Order orderToResponse = orderService.create(customerId, orderToCreate);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update some order.
     *
     * @param request {@link OrderRequest} request.
     * @return {@link OrderResponse} response mapped from updated order.
     */
    @RequestMapping(value = "/customers/{customerId}/orders", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody final OrderRequest request) {

        final Order orderToUpdate = dozerBeanMapper.map(request, Order.class);

        final Order orderToResponse = orderService.update(orderToUpdate);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch some order.
     *
     * @param orderId {@link Order} orderId.
     * @return {@link OrderResponse} response mapped from found order.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}", method = RequestMethod.GET)
    public ResponseEntity fetch(@PathVariable final Integer orderId) {

        final Order orderToResponse = orderService.fetch(orderId);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete some order.
     *
     * @param orderId {@link Order} orderId.
     * @return {@link OrderResponse} response mapped from deleted order.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable final Integer orderId) {

        final Order orderToResponse = orderService.delete(orderId);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch order by driver.
     *
     * @param driverId {@link Driver}.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/driver/{driverId}", method = RequestMethod.GET)
    public ResponseEntity fetchByDriver(@PathVariable final Integer driverId) {

        final List<Order> ordersToResponse = orderService.fetchByDriver(driverId);

        final List<OrderResponse> response = new ArrayList<>();

        ordersToResponse.forEach(order -> response.add(dozerBeanMapper.map(order, OrderResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch orders by customer.
     *
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders", method = RequestMethod.GET)
    public ResponseEntity fetchByCustomer(@PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchByCustomer(customerId);

        final List<OrderResponse> response = new ArrayList<>();

        ordersToResponse.forEach(order -> response.add(dozerBeanMapper.map(order, OrderResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch issued orders which belong to some customer.
     *
     * @param issued     {@link Order} issued.
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/issued/{issued}", method = RequestMethod.GET)
    public ResponseEntity fetchByIssuedAndCustomer(@PathVariable final boolean issued,
                                                   @PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchByIssuedAndCustomer(issued, customerId);

        final List<OrderResponse> response = new ArrayList<>();

        ordersToResponse.forEach(order -> response.add(dozerBeanMapper.map(order, OrderResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch completed orders which belong to some customer.
     *
     * @param completed  {@link Order} completed.
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/completed/{completed}", method = RequestMethod.GET)
    public ResponseEntity fetchByCompletedAndCustomer(@PathVariable final boolean completed,
                                                      @PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchByCompletedAndCustomer(completed, customerId);

        final List<OrderResponse> response = new ArrayList<>();

        ordersToResponse.forEach(order -> response.add(dozerBeanMapper.map(order, OrderResponse.class)));

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch paid orders which belong to some customer.
     *
     * @param paid       {@link Order} paid.
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @RequestMapping(value = "/customers/{customerId}/orders/paid/{paid}", method = RequestMethod.GET)
    public ResponseEntity fetchByPaidAndCustomer(@PathVariable final boolean paid,
                                                 @PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchByPaidAndCustomer(paid, customerId);

        final List<OrderResponse> response = new ArrayList<>();

        ordersToResponse.forEach(order -> response.add(dozerBeanMapper.map(order, OrderResponse.class)));

        return ResponseEntity.ok().body(response);
    }
}
