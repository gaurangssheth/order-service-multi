package com.example.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemResponse {

    private UUID id;
    private String productCode;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;

    public OrderItemResponse(UUID id, String productCode, int quantity,
                             BigDecimal unitPrice, BigDecimal lineTotal) {
        this.id = id;
        this.productCode = productCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }

    public UUID getId() { return id; }
    public String getProductCode() { return productCode; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getLineTotal() { return lineTotal; }
}