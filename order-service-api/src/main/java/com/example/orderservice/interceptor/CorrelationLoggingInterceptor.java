package com.example.orderservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class CorrelationLoggingInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(CorrelationLoggingInterceptor.class);

    private static final String CORRELATION_ID = "correlationId";
    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String correlationId = request.getHeader("X-Correlation-Id");

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        request.setAttribute(CORRELATION_ID, correlationId);
        request.setAttribute(START_TIME, System.currentTimeMillis());

        MDC.put(CORRELATION_ID, correlationId);

        response.setHeader("X-Correlation-Id", correlationId);

        log.info("START method={} path={}",
                request.getMethod(),
                request.getRequestURI());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {

        Long startTime = (Long) request.getAttribute(START_TIME);

        long durationMs = startTime == null
                ? 0
                : System.currentTimeMillis() - startTime;

        log.info("END method={} path={} status={} durationMs={}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                durationMs);

        MDC.remove(CORRELATION_ID);
    }
}