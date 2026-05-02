package com.example.orderservice.dto;

import com.example.orderservice.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

public class OrderResponse {

    private UUID id;
    private String customerEmail;
    private BigDecimal amount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    public OrderResponse(UUID id, String customerEmail, BigDecimal amount,
                         OrderStatus status, LocalDateTime createdAt, List<OrderItemResponse> items) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }

    public UUID getId() { return id; }
    public String getCustomerEmail() { return customerEmail; }
    public BigDecimal getAmount() { return amount; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItemResponse> getItems() { return items; }
}