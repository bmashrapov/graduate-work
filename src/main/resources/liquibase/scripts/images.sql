-- liquibase formatted sql
-- changeset BM:1
create table if not exists images (
    image_id   bigserial primary key
);
-- changeset BM:2
alter table users
    add column image_id bigint references images (image_id);
-- changeset BM:3
alter table ads
    add column image_id bigint references images (image_id);
