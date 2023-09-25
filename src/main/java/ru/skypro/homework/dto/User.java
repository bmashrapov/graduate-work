package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    Integer id;
    String email;
    String firstname;
    String lastName;
    String phone;
    Role role;
    String image;
}
