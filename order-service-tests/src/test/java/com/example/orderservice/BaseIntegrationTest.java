package com.example.orderservice;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

public abstract class BaseIntegrationTest {

    @LocalServerPort
    protected int port;

    protected final TestRestTemplate restTemplate = new TestRestTemplate();

    protected String url(String path) {
        return "http://localhost:" + port + path;
    }
}