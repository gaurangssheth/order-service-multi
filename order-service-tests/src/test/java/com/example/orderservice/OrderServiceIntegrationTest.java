package com.example.orderservice;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = OrderServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class OrderServiceIntegrationTest extends BaseIntegrationTest {

    @Test
    @Transactional
    void shouldCreateOrder() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerEmail("test@example.com");
        request.setAmount(new BigDecimal("99.99"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", "test-it-123");

        HttpEntity<CreateOrderRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<OrderResponse> response = restTemplate.postForEntity(
                url("/api/orders"),
                entity,
                OrderResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCustomerEmail()).isEqualTo("test@example.com");
        assertThat(response.getBody().getId()).isNotNull();
    }
}