-- liquibase formatted sql

-- changeset BM:1
create table if not exists ads
(
    ad_id       serial primary key,
    user_id     int  not null references users (user_id) on delete cascade,
    title       text not null,
    price       int  not null,
    description text
);