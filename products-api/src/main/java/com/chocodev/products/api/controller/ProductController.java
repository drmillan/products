package com.chocodev.products.api.controller;

import com.chocodev.products.api.model.*;
import com.chocodev.products.exception.ProductNotFoundException;
import com.chocodev.products.model.entity.Product;
import com.chocodev.products.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        CreateProductResponse createProductResponse = new CreateProductResponse();
        Product product = productService.createProduct(createProductRequest.getName(), createProductRequest.getPrice());
        createProductResponse.setId(product.getId());
        createProductResponse.setName(product.getName());
        return createProductResponse;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductListResponse getProducts() {
        return new ProductListResponse(productService.getProducts().stream().map(ProductItem::new).collect(Collectors.toList()));
    }

    @PutMapping(value = "/{productId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UpdateProductResponse updateProduct(@PathVariable("productId") Long productId, @RequestBody UpdateProductRequest updateProductRequest) throws ProductNotFoundException {
        UpdateProductResponse updateProductResponse = new UpdateProductResponse();
        productService.updateProduct(productId, updateProductRequest.getName(), updateProductRequest.getPrice());
        return updateProductResponse;
    }
}
