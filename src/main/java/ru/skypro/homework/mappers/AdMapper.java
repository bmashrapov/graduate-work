package ru.skypro.homework.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entities.AdEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdMapper {
//    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);

    @Mapping(source = "author", target = "author.id")
//    @Mapping(source = "title", target = "title")
//    @Mapping(source = "price", target = "price")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "image", target = "image")
    Ad adEntityToAd(AdEntity adEntity);

    @Mapping(source = "author.id", target = "author")
//    @Mapping(source = "title", target = "title")
//    @Mapping(source = "price", target = "price")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "image", target = "image")
    AdEntity adToAdEntity(Ad ad);
}
