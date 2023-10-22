package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entities.UserEntity;

@Component
public class UserMapper {
    public User entityToUserDto(UserEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getFirstName(),
                entity.getLastName(), entity.getPhone(), entity.getRole(), entity.getImagePath());
    }

    public UserEntity userDtoToEntity(UpdateUser user, UserEntity entity) {
        entity.setPhone(user.getPhone());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }

    public UserEntity registerReqDtoToEntity(Register req) {
        return new UserEntity(req.getPassword(), req.getUsername(), req.getFirstName(),
                req.getLastName(), req.getPhone(), req.getRole());
    }

    public UpdateUser entityToUpdateUserDto(UserEntity entity) {
        return new UpdateUser(entity.getFirstName(),
                entity.getLastName(), entity.getPhone());
    }

}
