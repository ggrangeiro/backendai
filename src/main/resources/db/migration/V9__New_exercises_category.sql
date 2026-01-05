ALTER TABLE exercises
    ADD COLUMN category ENUM('STANDARD', 'SPECIAL') NOT NULL DEFAULT 'STANDARD' AFTER name,
    ADD COLUMN tag VARCHAR(255) NULL AFTER category;

CREATE INDEX idx_exercises_category ON exercises (category);