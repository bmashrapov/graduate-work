package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("{id}/comments")
    public ResponseEntity<Comments> getComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> postComments(@PathVariable Integer id,
                                                @RequestBody CreateOrUpdateComment text, Authentication authentication) {
        return ResponseEntity.ok(commentService.add(id, text, authentication.getName()));
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    @PreAuthorize("@commentServiceImpl.getEntity(#commentId).author.email.equals(#auth.name) " +
            "or hasAuthority('DELETE_ANY_COMMENT')")
    public ResponseEntity<?> removeComment(@PathVariable int adId, @PathVariable int commentId, Authentication auth) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("@commentServiceImpl.getEntity(#commentId).author.email.equals(#auth.name) " +
            "or hasAuthority('UPDATE_ANY_COMMENT')")
    public ResponseEntity<Comment> updateComment(@PathVariable int commentId, @RequestBody Comment newComment,
                                                 Authentication auth, @PathVariable String adId) {
        return ResponseEntity.ok(commentService.update(commentId, newComment, auth.getName()));
    }

}
