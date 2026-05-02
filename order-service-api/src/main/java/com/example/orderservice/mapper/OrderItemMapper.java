package com.example.orderservice.mapper;

import com.example.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

public interface OrderItemMapper {

    void insertOrderItem(@Param("item") OrderItem item);

    List<OrderItem> findByOrderId(@Param("orderId") UUID orderId);
}