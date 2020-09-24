package com.chocodev.products.api.model;

import com.chocodev.products.model.entity.Product;

import java.math.BigDecimal;

public class ProductItem {
    private Long productId;
    private String name;
    private BigDecimal price;

    public ProductItem(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.productId = product.getId();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
