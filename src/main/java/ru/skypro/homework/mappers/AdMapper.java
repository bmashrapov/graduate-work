package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entities.AdEntity;

import org.springframework.stereotype.Component;
import ru.skypro.homework.entities.UserEntity;

@Component
public class AdMapper {
    public Ad entityToAdsDto(AdEntity entity) {
        return new Ad(entity.getAuthor().getId(), entity.getImagePath(),
                entity.getId(), entity.getPrice(), entity.getTitle());
    }

    public ExtendedAd entityToExtendedAdsDto(AdEntity entity) {
        return new ExtendedAd(entity.getId(), entity.getAuthor().getFirstName(), entity.getAuthor().getLastName(),
                entity.getAuthor().getUserName(), entity.getImagePath(),
                entity.getAuthor().getPhone(), entity.getPrice(), entity.getTitle());
    }

    public AdEntity createOrUpdateAdToEntity(CreateOrUpdateAd ads, UserEntity author) {
        return new AdEntity(author, ads.getTitle(), ads.getPrice(), ads.getDescription());
    }
}