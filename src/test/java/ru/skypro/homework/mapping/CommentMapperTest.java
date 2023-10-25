package ru.skypro.homework.mapping;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.CommentEntity;
import ru.skypro.homework.entities.ImageEntity;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.mappers.CommentMapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentMapperTest {

    private final CommentMapper commentMapper = new CommentMapper();

    @Test
    void testEntityToCommentDto() {
        UserEntity author = new UserEntity();
        author.setId(1);
        author.setImage(new ImageEntity());
        author.setFirstName("Jack");
        author.setLastName("Dave");

        LocalDateTime createdAt = LocalDateTime.now();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setAuthor(author);
        commentEntity.setCreatedAt(createdAt);
        commentEntity.setId(1);
        commentEntity.setText("comment");

        Comment expectedComment = new Comment(1, "/users/image/1", "Jack",
                createdAt.toInstant(ZoneOffset.ofHours(5)).toEpochMilli(), 1, "comment");

        Comment result = commentMapper.entityToCommentDto(commentEntity);

        assertEquals(expectedComment, result);
    }

    @Test
    void testCreateCommentToEntity() {
        CreateOrUpdateComment createOrUpdateComment = new CreateOrUpdateComment();
        createOrUpdateComment.setText("New comment");

        AdEntity ad = new AdEntity();
        UserEntity author = new UserEntity();

        CommentEntity result = commentMapper.commentToEntity(createOrUpdateComment, ad, author);

        assertEquals(createOrUpdateComment.getText(), result.getText());
        assertEquals(ad, result.getAd());
        assertEquals(author, result.getAuthor());
    }

}