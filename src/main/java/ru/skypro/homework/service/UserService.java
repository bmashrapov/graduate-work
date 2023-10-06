package ru.skypro.homework.service;

import ru.skypro.homework.dto.User;
import ru.skypro.homework.entities.UserEntity;

public interface UserService {
    User update(User user, String name);

    void delete(String name);

    User get(String name);

    UserEntity getEntity(String name);

    UserEntity getEntityById(int id);

    void changePassword(String newPassword, String name);

    boolean userExists(String username);

    void createUser(UserEntity user);
}
