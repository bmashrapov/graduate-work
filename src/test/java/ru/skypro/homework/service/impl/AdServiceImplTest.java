package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.entities.ImageEntity;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.mappers.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {
    @InjectMocks
    private AdServiceImpl adService;
    @Mock
    private AdRepository adRepository;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;
    @Mock
    private AdMapper mapper;
    @Mock
    private UserEntity user;
    private final int price = 100;
    private final int pk = 1;
    private final int id = 10;
    private final String title = "Phone";
    private final String description = "Phone Apple";
    private final String email = "xxx@mail.ru";
    private final String imagePath = "ads/image/" + pk;
    private AdEntity adEntity;

    @BeforeEach
    void setUp() {
        adEntity = new AdEntity(pk, user, title, price, description, null);
    }

    @Test
    void addTest() throws IOException {
        byte[] inputArray = "Test".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("fileName", inputArray);
        CreateOrUpdateAd properties = new CreateOrUpdateAd();
        properties.setTitle(title);
        properties.setPrice(price);
        properties.setDescription(description);
        when(userService.getEntity(email)).thenReturn(user);
        when(mapper.createOrUpdateAdToEntity(properties, user)).thenReturn(adEntity);
        when(adRepository.save(adEntity)).thenReturn(adEntity);
        when(mapper.entityToAdsDto(adEntity)).thenReturn(new Ad(id, imagePath, pk, price, title));

        Ad ad = adService.add(properties, mockMultipartFile, email);
        verify(imageService).saveImage(mockMultipartFile);
        assert ad.getAuthor() == id;
        assert ad.getTitle().equals(title);
        assert ad.getPk() == pk;
        assert ad.getPrice() == price;
        assert ad.getImage().equals("ads/image/" + pk);
    }

    @Test
    void getFullAdsByIdTest() {
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));
        String fName = "Oleg";
        String lName = "Olegov";
        String phone = "+78008889922";
        when(mapper.entityToExtendedAdsDto(adEntity)).thenReturn(new ExtendedAd(pk, fName, lName, description,
                email, imagePath, phone, price, title));

        ExtendedAd fullAds = adService.getFullAdsById(pk);
        assert fullAds.getPk() == pk;
        assert fullAds.getAuthorFirstName().equals(fName);
        assert fullAds.getAuthorLastName().equals(lName);
        assert fullAds.getDescription().equals(description);
        assert fullAds.getEmail().equals(email);
        assert fullAds.getImage().equals(imagePath);
        assert fullAds.getPhone().equals(phone);
        assert fullAds.getPrice() == price;
        assert fullAds.getTitle().equals(title);
    }

    @Test
    void deleteTest() throws IOException {
        adEntity.setImage(new ImageEntity());
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));
        adService.delete(pk);
        verify(adRepository).deleteById(pk);
        verify(imageService).deleteImage(adEntity.getImage());
    }

    @Test
    void updateTest() {
        String newDescription = "newDesc";
        String newTitle = "newTitle";
        int newPrice = 200;
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));
        CreateOrUpdateAd ads = new CreateOrUpdateAd();
        ads.setDescription(newDescription);
        ads.setTitle(newTitle);
        ads.setPrice(newPrice);
        AdEntity newEntity = new AdEntity(pk, user, newTitle, newPrice, newDescription, null);
        when(mapper.entityToAdsDto(newEntity)).thenReturn(new Ad(id, imagePath, pk, newPrice, newTitle));

        Ad ad = adService.update(pk, ads);
        verify(adRepository).save(newEntity);
        assert ad.getTitle().equals(newTitle);
        assert ad.getPrice() == newPrice;
    }

    @Test
    void uploadImageTest() throws IOException {
        byte[] inputArray = "Test".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("fileName", inputArray);
        ImageEntity imageEntity = new ImageEntity(id);
        adEntity.setImage(imageEntity);
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));

        adService.uploadImage(pk, mockMultipartFile);
        verify(imageService).saveImage(mockMultipartFile);
        verify(imageService).deleteImage(imageEntity);

    }

    @Test
    void getAllAdsTest() {
        AdEntity adEntity1 = new AdEntity();
        AdEntity adEntity2 = new AdEntity();
        Ad ad = new Ad(id, imagePath, pk, price, title);
        when(adRepository.findAll()).thenReturn(List.of(adEntity1, adEntity, adEntity2));
        when(mapper.entityToAdsDto(adEntity)).thenReturn(ad);
        when(mapper.entityToAdsDto(adEntity1)).thenReturn(new Ad());
        when(mapper.entityToAdsDto(adEntity2)).thenReturn(new Ad());

        Ads ads = adService.getAllAds();
        assert ads.getCount() == 3;
        Assertions.assertTrue(ads.getResults().contains(ad));
    }

    @Test
    void getAllMyAdsTest() {
        AdEntity adEntity1 = new AdEntity();
        Ad ad = new Ad(id, imagePath, pk, price, title);
        when(adRepository.findAllByAuthor_email(email)).thenReturn(List.of(adEntity1, adEntity));
        when(mapper.entityToAdsDto(adEntity)).thenReturn(ad);
        when(mapper.entityToAdsDto(adEntity1)).thenReturn(new Ad());

        Ads ads = adService.getAllMyAds(email);
        assert ads.getCount() == 2;
        Assertions.assertTrue(ads.getResults().contains(ad));
    }
}
