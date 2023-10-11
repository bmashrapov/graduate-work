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
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;
    private String title;
    private Integer price;
    private String description;
    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    public AdEntity(UserEntity author, String title, Integer price, String description) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.description = description;
    }
    public String getImagePath() {
        return image == null ? null : "/ads/image/" + id;
    }
}
