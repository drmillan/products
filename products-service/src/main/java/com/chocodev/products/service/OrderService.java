package com.chocodev.products.service;

import com.chocodev.products.exception.OrderNotFoundException;
import com.chocodev.products.exception.ProductNotFoundException;
import com.chocodev.products.exception.UserNotFoundException;
import com.chocodev.products.model.entity.Order;
import com.chocodev.products.model.entity.OrderProduct;
import com.chocodev.products.model.entity.Product;
import com.chocodev.products.model.entity.User;
import com.chocodev.products.model.repository.OrderProductRepository;
import com.chocodev.products.model.repository.OrderRepository;
import com.chocodev.products.model.repository.ProductRepository;
import com.chocodev.products.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    private OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        Order order = new Order();
        order.setUser(user.get());
        order.setCreationDate(new Date());
        orderRepository.save(order);
        return order;
    }

    public void addProductToOrder(Long productId, Long orderId) throws ProductNotFoundException, OrderNotFoundException {
        OrderProduct orderProduct = new OrderProduct();
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new OrderNotFoundException();
        }
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException();
        }
        orderProduct.setOrder(order.get());
        orderProduct.setProduct(product.get());
        orderProduct.setPrice(product.get().getPrice());
        if (order.get().getProducts() == null) {
            order.get().setProducts(new ArrayList<>());
        }
        order.get().getProducts().add(orderProduct);
        orderRepository.save(order.get());
    }

    public List<Order> findOrders(Date fromDate, Date toDate) {
        if (fromDate.after(toDate)) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    public Order findOrder(Long id) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new OrderNotFoundException();
        }
        return order.get();
    }
}
