INSERT INTO orders (
    id,
    customer_email,
    amount,
    status,
    created_at
)
VALUES (
    '11111111-1111-1111-1111-111111111111',
    'seed@example.com',
    25.50,
    'Pending',
    CURRENT_TIMESTAMP
);

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