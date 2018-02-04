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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Rest controller for {@link Order}.
 */
@RestController
@RequestMapping("/cargo/v1/customers/{customerId}/orders")
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
    @PostMapping
    public ResponseEntity createOrder(@PathVariable final Integer customerId, @RequestBody final OrderRequest request) {

        final Order orderToCreate = dozerBeanMapper.map(request, Order.class);

        final Order orderToResponse = orderService.createOrder(customerId, orderToCreate);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Method for update some order.
     *
     * @param request {@link OrderRequest} request.
     * @return {@link OrderResponse} response mapped from updated order.
     */
    @PutMapping
    public ResponseEntity updateOrder(@RequestBody final OrderRequest request) {

        final Order orderToUpdate = dozerBeanMapper.map(request, Order.class);

        final Order orderToResponse = orderService.updateOrder(orderToUpdate);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch some order.
     *
     * @param orderId {@link Order} orderId.
     * @return {@link OrderResponse} response mapped from found order.
     */
    @GetMapping(path = "/{orderId}")
    public ResponseEntity fetchOrder(@PathVariable final Integer orderId) {

        final Order orderToResponse = orderService.fetchOrder(orderId);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for delete some order.
     *
     * @param orderId {@link Order} orderId.
     * @return {@link OrderResponse} response mapped from deleted order.
     */
    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable final Integer orderId) {

        final Order orderToResponse = orderService.deleteOrder(orderId);

        final OrderResponse response = dozerBeanMapper.map(orderToResponse, OrderResponse.class);

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch order by driver.
     *
     * @param driverId {@link Driver}.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @RequestMapping(path = "/driver/{driverId}")
    public ResponseEntity fetchOrdersByDriver(@PathVariable final Integer driverId) {

        final List<Order> ordersToResponse = orderService.fetchOrdersByDriver(driverId);

        final List<OrderResponse> response = Optional.ofNullable(ordersToResponse)
                .map(orders -> orders.stream()
                        .map(order -> dozerBeanMapper.map(order, OrderResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch orders by customer.
     *
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @GetMapping
    public ResponseEntity fetchOrdersByCustomer(@PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchOrdersByCustomer(customerId);

        final List<OrderResponse> response = Optional.ofNullable(ordersToResponse)
                .map(orders -> orders.stream()
                        .map(order -> dozerBeanMapper.map(order, OrderResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch issued orders which belong to some customer.
     *
     * @param issued     {@link Order} issued.
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @GetMapping(path = "/issued/{issued}")
    public ResponseEntity fetchOrdersByIssuedAndCustomer(@PathVariable final boolean issued,
                                                         @PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchOrdersByIssuedAndCustomer(issued, customerId);

        final List<OrderResponse> response = Optional.ofNullable(ordersToResponse)
                .map(orders -> orders.stream()
                        .map(order -> dozerBeanMapper.map(order, OrderResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch completed orders which belong to some customer.
     *
     * @param completed  {@link Order} completed.
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @GetMapping(path = "/completed/{completed}")
    public ResponseEntity fetchOrdersByCompletedAndCustomer(@PathVariable final boolean completed,
                                                            @PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchOrdersByCompletedAndCustomer(completed, customerId);

        final List<OrderResponse> response = Optional.ofNullable(ordersToResponse)
                .map(orders -> orders.stream()
                        .map(order -> dozerBeanMapper.map(order, OrderResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }

    /**
     * Method for fetch paid orders which belong to some customer.
     *
     * @param paid       {@link Order} paid.
     * @param customerId {@link Customer} customerId.
     * @return list of {@link OrderResponse} responses mapped from list orders which found.
     */
    @GetMapping(path = "/paid/{paid}")
    public ResponseEntity fetchByPaidAndCustomer(@PathVariable final boolean paid,
                                                 @PathVariable final Integer customerId) {

        final List<Order> ordersToResponse = orderService.fetchOrdersByPaidAndCustomer(paid, customerId);

        final List<OrderResponse> response = Optional.ofNullable(ordersToResponse)
                .map(orders -> orders.stream()
                        .map(order -> dozerBeanMapper.map(order, OrderResponse.class))
                        .collect(toList()))
                .orElse(emptyList());

        return ResponseEntity.ok().body(response);
    }
}
