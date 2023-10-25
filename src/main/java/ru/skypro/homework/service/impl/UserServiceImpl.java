package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entities.ImageEntity;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.exception.FindNoEntityException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;


import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final ImageService imageService;

    @Override
    public UpdateUser update(UpdateUser user, String name) {
        return mapper.entityToUpdateUserDto(userRepository.save(mapper.userDtoToEntity(user, getEntity(name))));
    }

    @Override
    public void delete(String name) {
        userRepository.deleteByEmail(name);
    }

    @Override
    public User get(String name) {
        return mapper.entityToUserDto(getEntity(name));
    }

    @Override
    public UserEntity getEntity(String name) {
        return userRepository.findByEmail(name).orElseThrow(() -> new FindNoEntityException("User with name: " + name + "not found"));
    }

    @Override
    public UserEntity getEntityById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new FindNoEntityException("There is no user with the specified id"));
    }

    @Override
    public void changePassword(String newPassword, String name) {
        UserEntity entity = getEntity(name);
        entity.setPassword(newPassword);
        userRepository.save(entity);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByEmail(username).isPresent();
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void uploadImage(MultipartFile image, String name) throws IOException {
        UserEntity userEntity = getEntity(name);
        ImageEntity imageEntity = userEntity.getImage();
        userEntity.setImage(imageService.saveImage(image));
        userRepository.save(userEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
    }
}
