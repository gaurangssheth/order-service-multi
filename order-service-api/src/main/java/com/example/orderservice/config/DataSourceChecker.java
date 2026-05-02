package com.example.orderservice.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceChecker {

    private final DataSource dataSource;

    public DataSourceChecker(@Qualifier("orderDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void check() throws Exception {
        System.out.println("DataSource class = " + dataSource.getClass().getName());
        System.out.println("Connection URL = " + dataSource.getConnection().getMetaData().getURL());
    }
}