package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String email;
    private String firstname;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
