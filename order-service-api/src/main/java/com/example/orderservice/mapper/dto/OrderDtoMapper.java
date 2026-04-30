package com.example.orderservice.mapper.dto;

import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    OrderResponse toResponse(Order order);
}