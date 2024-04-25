package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;
    @Cacheable(cacheNames = "photos")
    @GetMapping("/user/{userId}/album/{albumId}")
    public ResponseEntity<List<Photo>> getPhotosByAlbumId(@PathVariable Long userId, @PathVariable Long albumId){
        List<Photo> photosList = photoService.getPhotosByUserIdAndAlbumId(userId, albumId);
        return ResponseEntity.ok(photosList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Photo>> getPhotosByUserId(@PathVariable Long userId){
        List<Photo> photoListByUser = photoService.getPhotosByUserId(userId);
        return ResponseEntity.ok(photoListByUser);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("albumId") Long albumId,
                                              @RequestParam("ownerId") User ownerId) {
        try {

            LocalDateTime date = LocalDateTime.now();
            String currentDirectory = System.getProperty("user.dir");

            String photoDirPath = currentDirectory + File.separator + "photosData";
            File photoDir = new File(photoDirPath);
            if (!photoDir.exists()) {
                photoDir.mkdirs();
            }

            String ownerIdDirPath = photoDirPath + File.separator + ownerId.getNom();
            File ownerIdDir = new File(ownerIdDirPath);
            if (!ownerIdDir.exists()) {
                ownerIdDir.mkdirs();
            }

            String albumIdDirPath = ownerIdDirPath + File.separator + albumId;
            File albumIdDir = new File(albumIdDirPath);
            if (!albumIdDir.exists()) {
                albumIdDir.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(albumIdDirPath, fileName);
            file.transferTo(filePath.toFile());


            Photo photo = new Photo(fileName, filePath.toString(), description, date, albumId, ownerId);
            photoService.savePhoto(photo);

            return ResponseEntity.ok().body("Photo uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
        }
    }

    private static String getString(Long albumId, User ownerId, String currentDirectory) {
        String photoDirPath = currentDirectory + File.separator + "photosData";
        File photoDir = new File(photoDirPath);
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }

        String ownerIdDirPath = photoDirPath + File.separator + ownerId.getNom();
        File ownerIdDir = new File(ownerIdDirPath);
        if (!ownerIdDir.exists()) {
            ownerIdDir.mkdirs();
        }

        String albumIdDirPath = ownerIdDirPath + File.separator + albumId;
        return albumIdDirPath;
    }

}