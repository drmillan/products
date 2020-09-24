package com.chocodev.products.model;


import com.chocodev.products.ProductsApplication;
import com.chocodev.products.exception.OrderNotFoundException;
import com.chocodev.products.exception.ProductNotFoundException;
import com.chocodev.products.exception.UserNotFoundException;
import com.chocodev.products.model.dto.OrderDTO;
import com.chocodev.products.model.entity.Order;
import com.chocodev.products.model.entity.Product;
import com.chocodev.products.model.entity.User;
import com.chocodev.products.model.mapper.OrderDTOMapper;
import com.chocodev.products.service.OrderService;
import com.chocodev.products.service.ProductService;
import com.chocodev.products.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProductsApplication.class)
public class OrderTest {
    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Test
    public void ordersCanNotBeCreatedWithInvalidUserTest() {
        assertThrows(UserNotFoundException.class, () -> {
            orderService.createOrder(0L);
        });
    }

    @Test
    public void ordersCanBeCreatedWithValidUserTest() throws UserNotFoundException {
        User user = userService.createUser("drm@chocodev.com");
        assertNotNull(orderService.createOrder(user.getId()));
    }

    @Test
    @Sql("/delete_tables.sql")
    public void ordersCanBeCreatedAndProductsAssigned() throws UserNotFoundException, ProductNotFoundException, OrderNotFoundException {

        int val1 = 13;
        int val2 = 27;
        Product product1 = productService.createProduct("sample_product_1", new BigDecimal(val1));
        Product product2 = productService.createProduct("sample_product_2", new BigDecimal(val2));
        User user = userService.createUser("drm@chocodev.com");
        Order order = orderService.createOrder(user.getId());
        orderService.addProductToOrder(product1.getId(), order.getId());
        orderService.addProductToOrder(product2.getId(), order.getId());
        Order updatedOrder = orderService.findOrder(order.getId());
        OrderDTO orderDTO = new OrderDTOMapper().map(updatedOrder);
        assertEquals(orderDTO.getTotal().compareTo(new BigDecimal(val1 + val2)), 0);
    }

    @Test
    @Sql("/delete_tables.sql")
    public void orderTotalsIsNotAffectedByPriceChanges() throws UserNotFoundException, OrderNotFoundException, ProductNotFoundException {
        int val1 = 13;
        int val2 = 27;
        Product product1 = productService.createProduct("sample_product_1", new BigDecimal(val1));
        Product product2 = productService.createProduct("sample_product_2", new BigDecimal(val2));
        User user = userService.createUser("drm@chocodev.com");
        Order order = orderService.createOrder(user.getId());
        orderService.addProductToOrder(product1.getId(), order.getId());
        orderService.addProductToOrder(product2.getId(), order.getId());
        Order updatedOrder = orderService.findOrder(order.getId());
        OrderDTO orderDTO = new OrderDTOMapper().map(updatedOrder);
        assertEquals(orderDTO.getTotal().compareTo(new BigDecimal(val1 + val2)), 0);
        productService.updateProductPrice(product1.getId(), new BigDecimal(10000));
        updatedOrder = orderService.findOrder(order.getId());
        orderDTO = new OrderDTOMapper().map(updatedOrder);
        assertEquals(orderDTO.getTotal().compareTo(new BigDecimal(val1 + val2)), 0);
    }
}
