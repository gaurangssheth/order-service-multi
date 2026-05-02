package com.example.orderservice.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

public class DataSourceConfig {

    public DataSource dataSource(
            @Value("${app.datasource.order.url}") String url,
            @Value("${app.datasource.order.username}") String username,
            @Value("${app.datasource.order.password}") String password,
            @Value("${app.datasource.order.driver-class-name}") String driverClassName) {

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        dataSource.setMaximumPoolSize(5);
        dataSource.setMinimumIdle(1);
        dataSource.setPoolName("OrderServiceHikariPool");

        return dataSource;
    }
}