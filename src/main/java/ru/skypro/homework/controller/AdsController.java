package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    private final AdService adService;

    @GetMapping
    public ResponseEntity getAds (){
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity postAds (@RequestPart CreateOrUpdateAd properties,
                                   @RequestPart MultipartFile image){
        AdEntity entity = new AdEntity();
        return ResponseEntity.ok(new Ad(entity.getAuthor().getId(), entity.getImagePath(), entity.getId(), entity.getPrice(), entity.getTitle()));
    }
    @GetMapping("{id}")
    public ResponseEntity getById (@PathVariable(name= "id") Integer id){
        AdEntity entity = new AdEntity();
        return ResponseEntity.ok(new ExtendedAd(entity.getId(), entity.getAuthor().getFirstName(), entity.getAuthor().getLastName(), entity.getAuthor().getUserName(), entity.getImagePath(), entity.getAuthor().getPhone(), entity.getPrice(), entity.getTitle()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeById (@PathVariable(name= "id") Integer id){
        return ResponseEntity.ok("");
    }

    @PatchMapping("{id}")
    public ResponseEntity updateAds (@PathVariable (name ="id") Integer id,
                                     @RequestBody CreateOrUpdateAd properties){
        AdEntity entity = new AdEntity();
        return ResponseEntity.ok(new Ad(entity.getAuthor().getId(), entity.getImagePath(), entity.getId(), entity.getPrice(), entity.getTitle()));
    }

    @GetMapping("/me")
    public ResponseEntity getAdsByRegisterUser (Authentication authentication){
        return ResponseEntity.ok(adService.getAllMyAds(authentication.getName()));
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateImage (@PathVariable(name = "id") Integer id,
                                       @RequestParam MultipartFile image){
        return ResponseEntity.ok("");
    }
}
