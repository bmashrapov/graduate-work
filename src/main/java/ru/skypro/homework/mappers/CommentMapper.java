package ru.skypro.homework.mappers;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.CommentEntity;
import ru.skypro.homework.entities.UserEntity;
@Component
public class CommentMapper {
    public Comment entityToCommentDto(CommentEntity entity) {
        return new Comment(entity.getAuthor().getId(), entity.getAuthor().getImagePath(),
                entity.getAuthor().getFirstName(), entity.getCreatedAt(),
                entity.getId(), entity.getText());
    }

    public CommentEntity commentToEntity(Comment createComment, AdEntity ad, UserEntity author) {
        return new CommentEntity(author, createComment.getCreatedAt(), createComment.getText(), ad);
    }

}