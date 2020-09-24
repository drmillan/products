package com.chocodev.products.service;

import com.chocodev.products.exception.ProductNotFoundException;
import com.chocodev.products.model.entity.Product;
import com.chocodev.products.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, BigDecimal price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
        return product;
    }

    @Transactional
    public void updateProduct(Long productId, String name, BigDecimal price) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.get() == null) {
            throw new ProductNotFoundException();
        } else {
            Product existingProduct = product.get();
            existingProduct.setName(name);
            existingProduct.setPrice(price);
            productRepository.save(existingProduct);
        }
    }

    @Transactional
    public void updateProductPrice(Long productId, BigDecimal price) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.get() == null) {
            throw new ProductNotFoundException();
        } else {
            Product existingProduct = product.get();
            existingProduct.setPrice(price);
            productRepository.save(existingProduct);
        }
    }

    public Product getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException();
        }
        return product.get();
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
