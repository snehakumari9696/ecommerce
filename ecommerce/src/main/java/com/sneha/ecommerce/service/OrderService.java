package com.sneha.ecommerce.service;

import com.sneha.ecommerce.dto.OrderRequest;
import com.sneha.ecommerce.model.Order;
import com.sneha.ecommerce.model.Product;
import com.sneha.ecommerce.repository.OrderRepository;
import com.sneha.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(OrderRequest orderRequest) {
        List<Product> products =orderRequest.getProductIds().stream().map(id ->productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found with id: "+id))).collect(Collectors.toList());
        productRepository.findAllById(orderRequest.getProductIds());

        double totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        Order order = new Order(orderRequest.getUserId(), products, totalAmount);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}

