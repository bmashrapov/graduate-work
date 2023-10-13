package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entities.AdEntity;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    private final AdService adService;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity getAds (){
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> postAds (@RequestPart CreateOrUpdateAd properties,
                                   @RequestPart MultipartFile image, Authentication authentication) throws IOException {
        return ResponseEntity.status(201).body(adService.add(properties,image,authentication.getName()));
    }
    @GetMapping("{id}")
    public ResponseEntity <ExtendedAd> getById (@PathVariable(name= "id") Integer id){
        return ResponseEntity.ok(adService.getFullAdsById(id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("@adServiceImpl.getEntity(#id).author.userName.equals(#auth.name) or hasAuthority('DELETE_ANY_AD')")
    public ResponseEntity<?> removeById (@PathVariable(name= "id") Integer id) throws IOException{
        adService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{id}")
    @PreAuthorize("@adServiceImpl.getEntity(#id).author.userName.equals(#auth.name) or hasAuthority('UPDATE_ANY_AD')")
    public ResponseEntity<Ad> updateAds (@PathVariable (name ="id") Integer id,
                                     @RequestBody CreateOrUpdateAd properties, Authentication authentication){
        return ResponseEntity.ok(adService.update(id, properties));
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsByRegisterUser (Authentication authentication){
        return ResponseEntity.ok(adService.getAllMyAds(authentication.getName()));
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage (@PathVariable(name = "id") Integer id,
                                       @RequestParam MultipartFile image) throws IOException{
        adService.uploadImage(id, image);
        return ResponseEntity.ok().build();
    }
}
