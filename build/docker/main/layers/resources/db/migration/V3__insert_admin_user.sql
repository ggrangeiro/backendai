INSERT INTO users (
    email,
    password,
    is_active,
    created_at,
    updated_at
)
SELECT
    'admin',
    '$2a$10$9Q0mD5z9LZpZrEw4n7A3kObf9c5XbF6j8nT2bJ5B6pHn2P6e5mZ2G',
    TRUE,
    CURRENT_TIMESTAMP(6),
    CURRENT_TIMESTAMP(6)
    WHERE NOT EXISTS (
  SELECT 1 FROM users WHERE email = 'admin'
);
