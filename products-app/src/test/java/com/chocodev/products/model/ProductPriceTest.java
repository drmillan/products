package com.chocodev.products.model;

import com.chocodev.products.ProductsApplication;
import com.chocodev.products.exception.ProductNotFoundException;
import com.chocodev.products.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProductsApplication.class)
public class ProductPriceTest {
    @Autowired
    ProductService productService;

    @Test
    public void productPriceCAnBeCreated() {
        Long productId = productService.createProduct("test_product", new BigDecimal(10)).getId();
    }

    @Test
    public void productPriceCAnBeUpdated() throws ProductNotFoundException {
        Long productId = productService.createProduct("test_product", new BigDecimal(10)).getId();
        productService.updateProductPrice(productId, new BigDecimal(20));
        assertEquals(productService.getProductById(productId).getPrice().compareTo(new BigDecimal(20)), 0);
    }

}
