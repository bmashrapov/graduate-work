-- liquibase formatted sql

-- changeset BM:1
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(32) NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(16),
    last_name VARCHAR(16),
    phone VARCHAR(15),
    role VARCHAR(15) not null
    );
