package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    @GetMapping
    public ResponseEntity getAds (){
        return ResponseEntity.ok(new Ads());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity postAds (@RequestBody CreateOrUpdateAd properties,
                                   @RequestParam MultipartFile image){
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("{id}")
    public ResponseEntity getById (@PathVariable(name= "id") Integer id){
        return ResponseEntity.ok(new ExtendedAd());
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeById (@PathVariable(name= "id") Integer id){
        return ResponseEntity.ok("");
    }

    @PatchMapping("{id}")
    public ResponseEntity updateAds (@PathVariable (name ="id") Integer id,
                                     @RequestBody CreateOrUpdateAd properties){
        return ResponseEntity.ok(new Ad());
    }

    @GetMapping("/me")
    public ResponseEntity getAdsByRegisterUser (){
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateImage (@PathVariable(name = "id") Integer id,
                                       @RequestParam MultipartFile image){
        return ResponseEntity.ok("");
    }
}
