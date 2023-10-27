-- liquibase formatted sql
-- changeset BM:1
insert into users(email, password, first_name, last_name, phone, role, image_id)
values ('superadmin@example.com',
        '$2a$10$V1zJ04Or3IwatD25TuCab.NMVb8Lo5etOyPZqKMKYPdcS2A1AT1be',
        'Admin',
        'Super',
        '+79000000000',
        'ADMIN',
        null)