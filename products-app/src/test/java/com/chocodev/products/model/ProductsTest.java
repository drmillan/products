package com.chocodev.products.model;


import com.chocodev.products.ProductsApplication;
import com.chocodev.products.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;


@SpringBootTest(classes = ProductsApplication.class)

public class ProductsTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testProductsCanBeCreatedWithNoPrice() {
        productService.createProduct("prod1", BigDecimal.TEN);
    }

    @Test
    public void testProductsCanBeRetrivedAsList() {
        // Initial product list size
        int originalSize = productService.getProducts().size();
        productService.createProduct(UUID.randomUUID().toString(), BigDecimal.TEN);
        productService.createProduct(UUID.randomUUID().toString(), BigDecimal.TEN);
        productService.createProduct(UUID.randomUUID().toString(), BigDecimal.TEN);
        productService.createProduct(UUID.randomUUID().toString(), BigDecimal.TEN);
        Assertions.assertEquals(productService.getProducts().size(), originalSize + 4);
    }
}
