package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class userController {
    @PatchMapping("/me")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("successfully");
        return new UserDTO();
    }
    @PatchMapping("/me")
    public UserDTO getUser(@RequestBody Long userId) {
        System.out.println("successfully");
        return new UserDTO();
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
