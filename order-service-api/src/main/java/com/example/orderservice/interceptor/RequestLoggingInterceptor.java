package com.example.orderservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID = "correlationId";
    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        String correlationId = request.getHeader("X-Correlation-Id");

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        request.setAttribute(CORRELATION_ID, correlationId);
        request.setAttribute(START_TIME, System.currentTimeMillis());

        response.setHeader("X-Correlation-Id", correlationId);

        System.out.printf("START Correlation ID: %s method=%s path=%s", correlationId, request.getMethod(), request.getRequestURI());
        System.out.println("Incoming request: "
                + request.getMethod()
                + " "
                + request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {

        String correlationId = (String) request.getAttribute(CORRELATION_ID);
        Long startTime = (Long) request.getAttribute(START_TIME);

        long durationMs = startTime == null
                ? 0
                : System.currentTimeMillis() - startTime;

        System.out.printf("End Correlation ID: %s method=%s path=%s status=%s duration=%dms",
                correlationId, request.getMethod(),
                request.getRequestURI(), response.getStatus(), durationMs);
        System.out.println("Completed request: "
                + request.getMethod()
                + " "
                + request.getRequestURI()
                + " Status: "
                + response.getStatus());
    }
}