package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {
    private Integer id;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
}
