package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entities.CommentEntity;

public interface CommentService {
    Comments getComments(int id);

    Comment add(int id, CreateOrUpdateComment comment, String name);

    void delete(int commentId);

    Comment update(int commentId, Comment newComment, String email);

    CommentEntity getEntity(int commentId);
}