package com.sneha.ecommerce.service;

import com.sneha.ecommerce.dto.OrderRequest;
import com.sneha.ecommerce.model.Order;
import com.sneha.ecommerce.model.Product;
import com.sneha.ecommerce.model.User;
import com.sneha.ecommerce.repository.OrderRepository;
import com.sneha.ecommerce.repository.ProductRepository;
import com.sneha.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(OrderRequest orderRequest) {
        User user=userRepository.findById(orderRequest.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"))
        ;

        List<Product>products =productRepository.findAllById(orderRequest.getProductIds());
        Order order= new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setStatus("PLACED!!!");
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }

}


