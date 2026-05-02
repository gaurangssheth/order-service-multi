package com.example.orderservice.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@MapperScan(
        basePackages = "com.example.orderservice.mapper",
        sqlSessionTemplateRef = "orderSqlSessionTemplate"
)
public class OrderMyBatisConfig {

    @Bean
    public DataSource orderDataSource(
            @Value("${app.datasource.order.url}") String url,
            @Value("${app.datasource.order.username}") String username,
            @Value("${app.datasource.order.password}") String password,
            @Value("${app.datasource.order.driver-class-name}") String driverClassName) {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setPoolName("OrderDataSourcePool");

        return dataSource;
    }

//    @Bean
    public DataSourceScriptDatabaseInitializer orderDatabaseInitializer(
            @Qualifier("orderDataSource") DataSource orderDataSource) {

        DatabaseInitializationSettings settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of(
                "classpath:db/schema/001-create-orders.sql",
                "classpath:db/schema/002-create-order-items.sql"));
        settings.setDataLocations(List.of("classpath:db/data/001-seed-orders.sql"));
        settings.setContinueOnError(false);

        return new DataSourceScriptDatabaseInitializer(orderDataSource, settings);
    }

    @Bean
//    @DependsOn("orderDatabaseInitializer")
    public SqlSessionFactory orderSqlSessionFactory(
            @Qualifier("orderDataSource") DataSource orderDataSource) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(orderDataSource);

        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/*.xml")
        );

        factoryBean.setTypeAliasesPackage("com.example.orderservice.model");
        factoryBean.setTypeHandlersPackage("com.example.orderservice.typehandler");

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate orderSqlSessionTemplate(
            @Qualifier("orderSqlSessionFactory") SqlSessionFactory orderSqlSessionFactory) {

        return new SqlSessionTemplate(orderSqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager orderTransactionManager(
            @Qualifier("orderDataSource") DataSource orderDataSource) {

        return new DataSourceTransactionManager(orderDataSource);
    }
}