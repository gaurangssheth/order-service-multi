package com.example.orderservice.dto;

import java.math.BigDecimal;

public class CreateOrderItemRequest {

    private String productCode;
    private int quantity;
    private BigDecimal unitPrice;

    // getters/setters

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}