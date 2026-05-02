package com.example.orderservice.mapper;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

@Mapper
public interface OrderMapper {

    void insertOrder(@Param("order") Order order);

    Order findById(@Param("id") UUID id);

    void insertOrderItem(@Param("item") OrderItem item);

    Order findByIdWithItems(@Param("id") UUID id);
}