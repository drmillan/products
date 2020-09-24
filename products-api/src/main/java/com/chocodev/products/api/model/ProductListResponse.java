package com.chocodev.products.api.model;

import java.util.ArrayList;
import java.util.List;

public class ProductListResponse extends BaseResponse {
    private List<ProductItem> products = new ArrayList<>();

    public ProductListResponse(List<ProductItem> products) {
        this.products.addAll(products);
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
    }
}
