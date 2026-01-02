CREATE TABLE training (
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

                          CONSTRAINT pk_training PRIMARY KEY (id),
                          CONSTRAINT fk_training_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE CASCADE
);

CREATE INDEX idx_training_user_id ON training(user_id);
