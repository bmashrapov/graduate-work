-- liquibase formatted sql
-- changeset BM:1
create table if not exists images (
    image_id   bigserial primary key
);
alter table users
    add column if not exists image_id bigint references images (image_id);
alter table ads
    add column if not exists image_id bigint references images (image_id);
