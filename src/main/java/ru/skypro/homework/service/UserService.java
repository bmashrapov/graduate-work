package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entities.UserEntity;

import java.io.IOException;

public interface UserService {
    UpdateUser update(UpdateUser updateUser, String name);

    void delete(String name);

    User get(String name);

    UserEntity getEntity(String name);

    UserEntity getEntityById(int id);

    void changePassword(String oldPassword, String newPassword, String name);

    boolean userExists(String username);

    void createUser(UserEntity user);
    void uploadImage(MultipartFile image, String name) throws IOException;
}
