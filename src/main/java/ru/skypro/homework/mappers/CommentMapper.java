package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entities.CommentEntity;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "createdAt", target = "createAt")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "ad", target = "pk")
    Comment commentEntityToComment(CommentEntity commentEntity);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "createAt", target = "createdAt")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "pk", target = "ad")
    CommentEntity commentToCommentEntity(Comment comment);
}
