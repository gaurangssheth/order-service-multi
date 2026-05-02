--liquibase formatted sql

--changeset gaurang:004-seed-order-items
INSERT INTO order_items (
    id,
    order_id,
    product_code,
    quantity,
    unit_price
)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    '11111111-1111-1111-1111-111111111111',
    'BOOK',
    2,
    10.00
);