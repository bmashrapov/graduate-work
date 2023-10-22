package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.ImageEntity;
import ru.skypro.homework.exception.FindNoEntityException;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdMapper mapper;

    @Override
    public Ad add(CreateOrUpdateAd properties, MultipartFile image, String email) throws IOException {
        AdEntity ad = mapper.createOrUpdateAdToEntity(properties, userService.getEntity(email));
        ad.setImage(imageService.saveImage(image));
        return mapper.entityToAdsDto(adRepository.save(ad));
    }

    @Override
    public ExtendedAd getFullAdsById(int id) {
        return mapper.entityToExtendedAdsDto(getEntity(id));
    }

    @Override
    public void delete(int id) throws IOException {
        ImageEntity image = getEntity(id).getImage();
        adRepository.deleteById(id);
        imageService.deleteImage(image);
    }

    @Override
    public Ad update(int id, CreateOrUpdateAd ads) {
        AdEntity entity = getEntity(id);
        entity.setTitle(ads.getTitle());
        entity.setDescription(ads.getDescription());
        entity.setPrice(ads.getPrice());
        adRepository.save(entity);
        return mapper.entityToAdsDto(entity);
    }

    @Override
    public AdEntity getEntity(int id) {
        return adRepository.findById(id).orElseThrow(() -> new FindNoEntityException("объявление"));
    }

    @Override
    public void uploadImage(int id, MultipartFile image) throws IOException {
        AdEntity adEntity = getEntity(id);
        ImageEntity imageEntity = adEntity.getImage();
        adEntity.setImage(imageService.saveImage(image));
        adRepository.save(adEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
    }

    @Override
    public Ads getAllAds() {
        return getWrapper(adRepository.findAll());
    }

    @Override
    public Ads getAllMyAds(String name) {
        return getWrapper(adRepository.findAllByAuthor_email(name));
    }

    private Ads getWrapper(List<AdEntity> list) {
        List<Ad> result = new LinkedList<>();
        list.forEach((entity -> result.add(mapper.entityToAdsDto(entity))));
        return new Ads(result.size(), result);
    }
}
