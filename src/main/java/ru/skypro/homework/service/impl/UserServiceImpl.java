package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
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
    public void changePassword(String oldPassword, String newPassword, String name) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(name);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            if (passwordsMatch(oldPassword, user.getPassword())) {
                String newPasswordHash = passwordEncoder.encode(newPassword);
                user.setPassword(newPasswordHash);
                userRepository.save(user);
            } else {
                throw new FindNoEntityException("Неправильный старый пароль");
            }
        } else {
            throw new FindNoEntityException("Пользователь не найден");
        }
    }

    private boolean passwordsMatch(String inputPassword, String storedPasswordHash) {
        return passwordEncoder.matches(inputPassword, storedPasswordHash);
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
