package com.example.orderservice.config;

import com.example.orderservice.interceptor.CorrelationLoggingInterceptor;
import com.example.orderservice.interceptor.RequestLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestLoggingInterceptor requestLoggingInterceptor;
    private final CorrelationLoggingInterceptor correlationLoggingInterceptor;

    public WebConfig(RequestLoggingInterceptor requestLoggingInterceptor,
                     CorrelationLoggingInterceptor correlationLoggingInterceptor) {
        this.requestLoggingInterceptor = requestLoggingInterceptor;
        this.correlationLoggingInterceptor = correlationLoggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(correlationLoggingInterceptor)
                .excludePathPatterns(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/h2-console/**"
                );
        registry.addInterceptor(requestLoggingInterceptor)
                .excludePathPatterns(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                );
    }
}