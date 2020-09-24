package com.chocodev.products.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long orderId;
    private Long userId;

    private List<OrderProductDTO> products = new ArrayList<>();
    private BigDecimal total;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
