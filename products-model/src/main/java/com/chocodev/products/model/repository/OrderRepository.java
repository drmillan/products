package com.chocodev.products.model.repository;

import com.chocodev.products.model.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "from Order o where creationDate BETWEEN :fromDate AND :toDate")
    List<Order> findBetweenDates(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
