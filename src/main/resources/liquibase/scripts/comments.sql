-- liquibase formatted sql

-- changeset BM:1
create table if not exists comments
(
    comment_id     serial primary key,
    user_id        int not null references users(user_id) on delete cascade,
    created_at     timestamp not null ,
    comments_text  text,
    ad_id int not null references ads (ad_id) on delete cascade
);

