package com.chocodev.products.model.repository;

import com.chocodev.products.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.name = :name where p.id = :productId")
    int updateProductName(@Param("productId") Long productId, @Param("name") String name);
}
