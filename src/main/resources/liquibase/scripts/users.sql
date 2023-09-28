-- liquibase formatted sql

-- changeset BM:1
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(16) NOT NULL,
    first_name VARCHAR(16),
    last_name VARCHAR(16),
    phone VARCHAR(15),
    role VARCHAR(15) NOT NULL CHECK (role IN ('Admin', 'User'))
    );
-- changeset BM:2
ALTER TABLE users
    RENAME COLUMN id TO user_id;