package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entities.CommentEntity;
import ru.skypro.homework.exception.FindNoEntityException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final AdService adService;
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Override
    public Comments getComments(int id) {
        List<Comment> result = new LinkedList<>();
        commentRepository.findAllByAd_Id(id).forEach(entity -> result.add(mapper.entityToCommentDto(entity)));
        return new Comments(result.size(), result);
    }

    @Override
    public Comment add(int id, Comment comment, String name) {
        CommentEntity entity = mapper.commentToEntity(comment, adService.getEntity(id), userService.getEntity(name));
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment update(int commentId, Comment comment, String email) {
        CommentEntity entity = getEntity(commentId);
        entity.setText(comment.getText() + "(отредактировал(а) " + userService.getEntity(email).getFirstName() +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(" dd MMMM yyyy в HH:mm:ss)")));
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public CommentEntity getEntity(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindNoEntityException("There is no comment with the specified id"));
    }
}
