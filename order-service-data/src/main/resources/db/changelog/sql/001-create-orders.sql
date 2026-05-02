--liquibase formatted sql

--changeset gaurang:001-create-orders
CREATE TABLE orders (
    id VARCHAR(36) PRIMARY KEY,
    customer_email VARCHAR(255) NOT NULL,
    amount DECIMAL(18, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);