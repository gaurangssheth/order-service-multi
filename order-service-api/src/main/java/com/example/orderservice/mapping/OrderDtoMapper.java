package com.example.orderservice.mapping;

import com.example.orderservice.dto.OrderItemResponse;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    OrderResponse toResponse(Order order);

    default List<OrderItemResponse> toItemResponses(List<OrderItem> items) {
        if (items == null) {
            return List.of();
        }

        return items.stream()
                .map(this::toItemResponse)
                .toList();
    }

    @Mapping(target = "lineTotal", expression = "java(calculateLineTotal(item))")
    OrderItemResponse toItemResponse(OrderItem item);

    default BigDecimal calculateLineTotal(OrderItem item) {
        if (item == null || item.getUnitPrice() == null) {
            return BigDecimal.ZERO;
        }

        return item.getUnitPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));
    }
}