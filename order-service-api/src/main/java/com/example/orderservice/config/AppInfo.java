package com.example.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfo {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.environment}")
    private String environment;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getDbUserName() {
        return dbUserName;
    }
}
