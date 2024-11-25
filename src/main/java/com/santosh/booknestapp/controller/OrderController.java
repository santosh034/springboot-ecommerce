package com.santosh.booknestapp.controller;

import com.santosh.booknestapp.model.Order;
import com.santosh.booknestapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getUserOrders(@RequestParam Integer userId){
        List<Order> userOrders=orderService.getUserOrders(userId);
        return ResponseEntity.ok(userOrders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order orderPlaced=orderService.createOrder(order);
        return ResponseEntity.ok(orderPlaced);
    }
}
