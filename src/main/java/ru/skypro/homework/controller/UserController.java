package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    @PostMapping("/set_password")
    public ResponseEntity updatePassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok("");
    }

    @GetMapping("/me")
    public ResponseEntity getInfoAboutRegisterUser (Authentication authentication){
        return ResponseEntity.ok(userService.getEntity(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(new UpdateUser());
    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAvatar(@RequestParam MultipartFile image) {
        return ResponseEntity.ok("");
    }

}
