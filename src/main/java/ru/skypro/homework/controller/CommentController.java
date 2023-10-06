package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("{id}/comments")
    public ResponseEntity getComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity postComments(@PathVariable Integer id,
                                       @RequestBody CreateOrUpdateComment text, Authentication authentication) {
        return ResponseEntity.ok(commentService.add(id, text, authentication.getName()));
    }

    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity removeComments(@PathVariable(name = "adId") Integer adId,
                                         @PathVariable(name = "commentsId") Integer commentId) {
        return ResponseEntity.ok("");
    }

    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity updateComments(@PathVariable(name = "adId") Integer adId,
                                         @PathVariable(name = "commentsId") Integer commentsId,
                                         @RequestBody CreateOrUpdateComment text) {
        return ResponseEntity.ok(new Comment());
    }

}
