CREATE TABLE analysis (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          type VARCHAR(50) NOT NULL,
                          created_at TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
                          result_data JSON NOT NULL,
                          score INT GENERATED ALWAYS AS (
                              CAST(result_data->>"$.score" AS SIGNED)
                              ) VIRTUAL,
                          repetitions INT GENERATED ALWAYS AS (
                              CAST(result_data->>"$.repetitions" AS SIGNED)
                              ) VIRTUAL,
                          INDEX idx_user_id (user_id),
                          INDEX idx_user_created (user_id, created_at DESC),
                          INDEX idx_type_score (type, score)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;