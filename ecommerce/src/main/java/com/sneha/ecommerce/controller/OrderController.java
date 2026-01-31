package com.sneha.ecommerce.controller;

import com.sneha.ecommerce.dto.OrderRequest;
import com.sneha.ecommerce.model.Order;
import com.sneha.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order=orderService.createOrder(orderRequest);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>>getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders= orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
}
