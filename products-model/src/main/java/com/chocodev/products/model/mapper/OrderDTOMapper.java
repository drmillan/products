package com.chocodev.products.model.mapper;

import com.chocodev.products.model.dto.OrderDTO;
import com.chocodev.products.model.entity.Order;
import com.chocodev.products.model.entity.OrderProduct;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class OrderDTOMapper implements Mapper<Order, OrderDTO> {
    @Override
    public OrderDTO map(Order order) {
        OrderProductDTOMapper mapper = new OrderProductDTOMapper();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setOrderId(order.getId());
        orderDTO.setTotal(order.getProducts().stream().map(OrderProduct::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        orderDTO.setProducts(order.getProducts().stream().map(mapper::map).collect(Collectors.toList()));
        return orderDTO;
    }
}
