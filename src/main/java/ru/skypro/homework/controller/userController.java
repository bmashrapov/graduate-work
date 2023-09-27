package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    @PatchMapping("/me")
    public User updateUser(@RequestBody User userDTO) {
        System.out.println("successfully");
        return new User();
    }
    @PatchMapping("/me")
    public User getUser(@RequestBody Long userId) {
        System.out.println("successfully");
        return new User();
    }
    @PatchMapping("/set_password")
    public ResponseEntity<String> updatePassword(@RequestParam("password") String newPassword) {
        System.out.println("successfully");

        return ResponseEntity.ok("Password updated successfully");
    }
    @PatchMapping("/me/image")
    public ResponseEntity<String> updateAvatar(@RequestParam("image") MultipartFile image) {

        return ResponseEntity.ok("Avatar updated successfully");
    }
}
