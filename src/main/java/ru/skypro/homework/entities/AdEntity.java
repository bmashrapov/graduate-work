package ru.skypro.homework.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdEntity {
    @Id
    @Column(name = "ad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;
    private String title;
    private int price;
    private String description;

    public AdEntity(UserEntity author, String title, int price, String description) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
    }

}