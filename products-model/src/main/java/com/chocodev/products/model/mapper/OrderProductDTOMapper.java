package com.chocodev.products.model.mapper;

import com.chocodev.products.model.dto.OrderProductDTO;
import com.chocodev.products.model.entity.OrderProduct;

public class OrderProductDTOMapper implements Mapper<OrderProduct, OrderProductDTO> {
    @Override
    public OrderProductDTO map(OrderProduct orderProduct) {
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setName(orderProduct.getProduct().getName());
        orderProductDTO.setPrice(orderProduct.getPrice());
        return orderProductDTO;
    }
}
