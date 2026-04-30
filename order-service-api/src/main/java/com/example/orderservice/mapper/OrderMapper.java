package com.example.orderservice.mapper;

import com.example.orderservice.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.UUID;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);

    Order findById(UUID id);
}