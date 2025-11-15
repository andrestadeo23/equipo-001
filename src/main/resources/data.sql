INSERT INTO users (id, username, email, password, created_at)
VALUES ('00000000-0000-0000-0000-000000000001', 'juan', 'juan@example.com', 'hashedpass', CURRENT_TIMESTAMP);

INSERT INTO products (id, name, description, category, popularity_score)
VALUES ('00000000-0000-0000-0000-000000000002', 'Laptop', 'High-end laptop', 'Electronics', 95);

INSERT INTO ratings (id, user_id, product_id, score, created_at)
VALUES ('00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000002', 5, CURRENT_TIMESTAMP);