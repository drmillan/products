package com.chocodev.products.api.controller;

import com.chocodev.products.api.model.*;
import com.chocodev.products.exception.OrderNotFoundException;
import com.chocodev.products.exception.ProductNotFoundException;
import com.chocodev.products.exception.UserNotFoundException;
import com.chocodev.products.model.entity.Order;
import com.chocodev.products.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    private OrderService orderService;

    private OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) throws UserNotFoundException {
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        Order order = orderService.createOrder(createOrderRequest.getUserId());
        createOrderResponse.setOrderId(order.getId());
        return createOrderResponse;
    }

    @PostMapping(value = "/{orderId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public AddProductToOrderResponse addProductToOrder(@PathVariable("orderId") Long orderId, @RequestBody AddProductToOrderRequest addProductToOrderRequest) throws OrderNotFoundException, ProductNotFoundException {
        AddProductToOrderResponse addProductToOrderResponse = new AddProductToOrderResponse();
        orderService.addProductToOrder(addProductToOrderRequest.getProductId(), orderId);
        return addProductToOrderResponse;
    }

    @GetMapping
    public OrderListResponse getOrders(@RequestParam String fromDateString, @RequestParam String toDateString) throws IllegalArgumentException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date fromDate = simpleDateFormat.parse(fromDateString);
        Date toDate = simpleDateFormat.parse(toDateString);
        List<Order> orders = orderService.findOrders(fromDate, toDate);
        return new OrderListResponse();
    }
}
