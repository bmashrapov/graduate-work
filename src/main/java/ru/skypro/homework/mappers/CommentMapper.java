package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.CommentEntity;
import ru.skypro.homework.entities.UserEntity;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
//public interface CommentMapper {
////    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
//
//    @Mapping(source = "author.id", target = "author")
//    @Mapping(source = "createdAt", target = "createAt")
//    @Mapping(source = "authorImage", target = "authorImage")
//    @Mapping(source = "authorFirstName", target = "authorFirstName")
//    @Mapping(source = "text", target = "text")
//    @Mapping(source = "ad.id", target = "pk")
//    Comment commentEntityToComment(CommentEntity commentEntity);
//
//    @Mapping(source = "author", target = "author.id")
//    @Mapping(source = "createAt", target = "createdAt")
//    @Mapping(source = "text", target = "text")
//    @Mapping(source = "pk", target = "ad.id")
//    @Mapping(source = "authorImage", target = "authorImage")
//    @Mapping(source = "authorFirstName", target = "authorFirstName")
//    CommentEntity commentToCommentEntity(CreateOrUpdateComment comment, AdEntity entity, UserEntity entity1);
//
//}
@Component
public class CommentMapper {
    public Comment entityToCommentDto(CommentEntity entity) {
        return new Comment(entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), entity.getCreatedAt(),
                entity.getId(), entity.getText());
    }

    public CommentEntity commentToEntity(Comment createComment, AdEntity ad, UserEntity author) {
        return new CommentEntity(author, createComment.getCreateAt(), createComment.getText(), ad);
    }

}