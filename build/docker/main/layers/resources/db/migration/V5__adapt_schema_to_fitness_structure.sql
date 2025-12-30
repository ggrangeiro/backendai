
ALTER TABLE users
    ADD COLUMN weight_kg DECIMAL(5,2) NULL,
  ADD COLUMN height_cm DECIMAL(5,2) NULL,
  ADD COLUMN body_fat_pct DECIMAL(5,2) NULL,
  ADD COLUMN target_body_fat_pct DECIMAL(5,2) NULL,
  ADD COLUMN personal_id BIGINT NULL,
  ADD COLUMN sex ENUM('MALE','FEMALE','OTHER') NULL,
  ADD COLUMN role ENUM('USER','PERSONAL','ADMIN') NOT NULL DEFAULT 'USER';

ALTER TABLE users
    ADD CONSTRAINT fk_users_personal
        FOREIGN KEY (personal_id) REFERENCES users(id)
            ON DELETE SET NULL;

CREATE INDEX idx_users_personal_id ON users(personal_id);
CREATE INDEX idx_users_role ON users(role);

UPDATE users
SET role = 'ADMIN'
WHERE email = 'admin';



CREATE TABLE exercises (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL,
                           created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                           updated_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
                           PRIMARY KEY (id),
                           UNIQUE KEY uq_exercises_name (name)
) ENGINE=InnoDB;

CREATE TABLE user_exercises (
                                user_id BIGINT NOT NULL,
                                exercise_id BIGINT NOT NULL,
                                created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
                                PRIMARY KEY (user_id, exercise_id),
                                CONSTRAINT fk_user_exercises_user
                                    FOREIGN KEY (user_id) REFERENCES users(id)
                                        ON DELETE CASCADE,
                                CONSTRAINT fk_user_exercises_exercise
                                    FOREIGN KEY (exercise_id) REFERENCES exercises(id)
                                        ON DELETE CASCADE,
                                KEY idx_user_exercises_exercise_id (exercise_id)
) ENGINE=InnoDB;



CREATE TABLE evolutions (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            user_id BIGINT NOT NULL,
                            overall_score DECIMAL(6,2) NULL,
                            body_fat_percentage DECIMAL(5,2) NULL,
                            visual_body_composition TEXT NULL,
                            torso_proportion TEXT NULL,
                            shoulder_symmetry TEXT NULL,
                            overall_posture TEXT NULL,
                            created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),

                            PRIMARY KEY (id),
                            CONSTRAINT fk_evolutions_user
                                FOREIGN KEY (user_id) REFERENCES users(id)
                                    ON DELETE CASCADE,

                            KEY idx_evolutions_user_id (user_id),
                            KEY idx_evolutions_created_at (created_at)
) ENGINE=InnoDB;
