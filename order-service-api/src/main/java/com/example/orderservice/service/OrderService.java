package com.example.orderservice.service;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.exception.NotFoundException;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.mapper.dto.OrderDtoMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderDtoMapper orderDtoMapper;

    public OrderService(OrderMapper orderMapper, OrderDtoMapper orderDtoMapper) {
        this.orderMapper = orderMapper;
        this.orderDtoMapper = orderDtoMapper;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();

        order.setId(UUID.randomUUID());
        order.setCustomerEmail(request.getCustomerEmail());
        order.setAmount(request.getAmount());
        order.setStatus(OrderStatus.Pending);
        order.setCreatedAt(LocalDateTime.now());

        orderMapper.insertOrder(order);

        return orderDtoMapper.toResponse(order);
    }

    public OrderResponse getOrder(UUID id) {
        Order order = orderMapper.findById(id);

        if (order == null) {
            throw new NotFoundException("Order not found with id: " + id);
        }

        return orderDtoMapper.toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerEmail(),
                order.getAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}