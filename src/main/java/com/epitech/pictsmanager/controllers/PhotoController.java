package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.Responses.GenericResponse;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping("/getPhotos")
    public List<Photo> getPhotos(){
        return photoService.getPhotos();
    }

    @PostMapping("/upload")
    public ResponseEntity<GenericResponse> uploadPhoto(@RequestParam("image") MultipartFile image, @RequestParam("name") String name, @RequestParam("path") String path, @RequestParam("description") String description, @RequestParam("date") String dateString, @RequestParam("album_id") Long albumId, @RequestParam("owner_id") Long ownerId) {
        try {
            LocalDateTime date = LocalDateTime.parse(dateString); // Convertir la chaîne de date en LocalDateTime si nécessaire
            User owner = photoService.findUserById(ownerId);
            if (owner == null) {
                return ResponseEntity.badRequest().body(new GenericResponse("Owner with id " + ownerId + " not found", HttpStatus.BAD_REQUEST.value()));
            }
            photoService.savePhoto(image, name, path, description, date, albumId, owner);
            return ResponseEntity.ok().body(new GenericResponse("Photo uploaded successfully", HttpStatus.OK.value()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse("Failed to upload photo", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
    
    @GetMapping("/image/{photoId}")
    public ResponseEntity<byte[]> getPhotoImage(@PathVariable Long photoId) {
        byte[] imageBytes = photoService.getPhotoImage(photoId);
        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
