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