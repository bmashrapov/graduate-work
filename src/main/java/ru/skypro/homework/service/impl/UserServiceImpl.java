package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.exception.FindNoEntityException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public User update(User user, String name) {
        return mapper.entityToUserDto(userRepository.save(mapper.userDtoToEntity(user, getEntity(name))));
    }

    @Override
    public void delete(String name) {
        userRepository.deleteByUserName(name);
    }

    @Override
    public User get(String name) {
        return mapper.entityToUserDto(getEntity(name));
    }

    @Override
    public UserEntity getEntity(String name) {
        return userRepository.findByUserName(name).orElseThrow(() -> new FindNoEntityException("User with name: " + name + "not found"));
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
        return userRepository.findByUserName(username).isPresent();
    }

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }
}
