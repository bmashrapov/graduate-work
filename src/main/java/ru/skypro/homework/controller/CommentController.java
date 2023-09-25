package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {


    @GetMapping("{id}/comments")
    public ResponseEntity getComment (@PathVariable Integer id){
        return ResponseEntity.ok(new Comments());
    }

    @PostMapping("{id}/comments")
    public ResponseEntity postComments (@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateComment text){
        return ResponseEntity.ok(new Comment());
    }

    @DeleteMapping("{adId}/comments/{commentsId}")
    public ResponseEntity removeComments (@PathVariable(name = "adId") Integer adId,
                                          @PathVariable(name = "commentsId") Integer commentId){
        return ResponseEntity.ok("");
    }

    @PatchMapping("{adId}/comments/{commentsId}")
    public ResponseEntity updateComments (@PathVariable (name = "adId") Integer adId,
                                          @PathVariable(name = "commentsId") Integer commentsId,
                                          @RequestBody CreateOrUpdateComment text){
        return ResponseEntity.ok(new Comment());
    }

}
