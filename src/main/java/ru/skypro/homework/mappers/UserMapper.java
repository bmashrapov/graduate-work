package ru.skypro.homework.mappers;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingConstants;
//import ru.skypro.homework.dto.User;
//import ru.skypro.homework.entities.UserEntity;
//
//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
//public interface UserMapper {
////    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "userName", target = "email")
//    @Mapping(source = "firstName", target = "firstname")
//    @Mapping(source = "lastName", target = "lastName")
//    @Mapping(source = "phone", target = "phone")
//    @Mapping(source = "role", target = "role")
//    @Mapping(source = "image", target = "image")
//    User userEntityToUser(UserEntity userEntity);
//
//        @Mapping(source = "id", target = "id")
//    @Mapping(source = "email", target = "userName")
//        @Mapping(source = "firstname", target = "firstName")
//        @Mapping(source = "lastName", target = "lastName")
//        @Mapping(source = "phone", target = "phone")
//        @Mapping(source = "role", target = "role")
//        @Mapping(source = "image", target = "image")
//    UserEntity userToUserEntity(User user, UserEntity entity);
//}
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entities.UserEntity;

@Component
public class UserMapper {
    public User entityToUserDto(UserEntity entity) {
        return new User(entity.getId(), entity.getUserName(), entity.getFirstName(),
                entity.getLastName(), entity.getPhone(), entity.getRole(), entity.getImagePath());
    }

    public UserEntity userDtoToEntity(User user, UserEntity entity) {
        entity.setPhone(user.getPhone());
        entity.setFirstName(user.getFirstname());
        entity.setLastName(user.getLastName());
        return entity;
    }

    public UserEntity registerReqDtoToEntity(Register req) {
        return new UserEntity(req.getPassword(), req.getUsername(), req.getFirstName(),
                req.getLastName(), req.getPhone(), req.getRole());
    }
}
