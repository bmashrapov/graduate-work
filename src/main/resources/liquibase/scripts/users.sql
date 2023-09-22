-- liquibase formatted sql

-- changeset BM:1
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    phone VARCHAR(15),
    role VARCHAR(15) NOT NULL CHECK (role IN ('Admin', 'User'))
    );