package com.rudra.waservicedev.Controllers;

import com.rudra.waservicedev.Dtos.OrderRequest;
import com.rudra.waservicedev.Models.Order;
import com.rudra.waservicedev.Models.WebhookRequest;
import com.rudra.waservicedev.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/msg")
    public String msg(){
        return "hello";
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        String order = orderService.placeOrder(orderRequest.getProductName(), orderRequest.getQuantity(), orderRequest.getCustomerName(), orderRequest.getCustomerPhone());
        return ResponseEntity.ok(order);
    }

    // Other methods as needed
}
