CREATE TABLE diet (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      user_id BIGINT NOT NULL,
                      content TEXT NOT NULL,
                      goal ENUM (
        'WEIGHT_LOSS',
        'HYPERTROPHY',
        'MAINTENANCE',
        'DEFINITION'
    ) NOT NULL,
                      created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),

                      CONSTRAINT pk_diet PRIMARY KEY (id),
                      CONSTRAINT fk_diet_user
                          FOREIGN KEY (user_id)
                              REFERENCES users(id)
                              ON DELETE CASCADE
);

CREATE INDEX idx_diet_user_id ON diet(user_id);