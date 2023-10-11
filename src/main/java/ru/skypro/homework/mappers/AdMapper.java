package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entities.AdEntity;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
//public interface AdMapper {
////    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
//
//    @Mapping(source = "author.id", target = "author")
////    @Mapping(source = "title", target = "title")
////    @Mapping(source = "price", target = "price")
////    @Mapping(source = "description", target = "description")
//    @Mapping(source = "image", target = "image")
//    @Mapping(source = "id", target = "pk")
//    Ad adEntityToAd(AdEntity adEntity);
//
//    @Mapping(source = "author", target = "author.id")
////    @Mapping(source = "title", target = "title")
////    @Mapping(source = "price", target = "price")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "image", target = "image")
//    @Mapping(source = "pk", target = "id")
//    AdEntity adToAdEntity(Ad ad);
//}
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

    public AdEntity CreateOrUpdateAdToEntity(CreateOrUpdateAd ads, UserEntity author) {
        return new AdEntity(author, ads.getTitle(), ads.getPrice(), ads.getDescription());
    }
}