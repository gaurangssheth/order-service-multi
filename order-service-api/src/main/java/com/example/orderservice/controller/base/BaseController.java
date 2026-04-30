package com.example.orderservice.controller.base;

import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {

    protected String getUserId(HttpServletRequest request) {
        return request.getHeader("X-User-ID");
    }

    protected String getCorrelationId(HttpServletRequest request) {
        return request.getHeader("X-Correlation-Id");
    }
}