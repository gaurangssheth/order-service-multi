package com.example.orderservice.controller;

import com.example.orderservice.config.AppInfo;
import com.example.orderservice.controller.base.BaseController;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;
    private final AppInfo appInfo;

    public OrderController(OrderService orderService, AppInfo appInfo) {
        this.orderService = orderService;
        this.appInfo = appInfo;
    }

    @GetMapping("/info")
    public ResponseEntity<String> getAppInfo() {
        String info = String.format("Application: %s, Version: %s, Environment: %s, DB User: %s",
                appInfo.getAppName(), appInfo.getAppVersion(), appInfo.getEnvironment(), appInfo.getDbUserName());
        return ResponseEntity.ok(info);
    }

    @PostMapping
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<OrderResponse> createOrder(
            @RequestHeader(value = "X-User-ID", required = false) String userId,
            @Valid @RequestBody CreateOrderRequest request) {

        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.accepted().body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID orderId) {
        OrderResponse response = orderService.getOrder(orderId);
        return ResponseEntity.ok(response);
    }
}