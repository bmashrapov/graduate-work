package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String firstname;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
