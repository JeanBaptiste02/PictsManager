package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.PhotoService;
import com.epitech.pictsmanager.service.UserService;
import com.epitech.pictsmanager.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

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

    @GetMapping("/user/{userId}/visibility/{visibility}")
    public ResponseEntity<List<Photo>> getPhotosByUserIdAndVisibility(@PathVariable Long userId, @PathVariable boolean visibility) {
        List<Photo> photos = photoService.getPhotosByUserIdAndVisibility(userId, visibility);
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/public/photos")
    public ResponseEntity<List<Photo>> getAllPublicPhotos() {
        List<Photo> publicPhotos = photoService.getAllPublicPhotos();
        return ResponseEntity.ok(publicPhotos);
    }

    @GetMapping("/album/{albumId}")
    public ResponseEntity<List<Photo>> getPhotosByAlbumId(@PathVariable Album albumId) {
        List<Photo> photos = photoService.getPhotosByAlbumId(albumId);
        return ResponseEntity.ok(photos);
    }

    @DeleteMapping("delete/{photoId}")
    public ResponseEntity<String> delete(@PathVariable Long photoId, HttpServletRequest request) {

        String token = extractTokenFromRequest(request);
        if (token != null) {
            User existingUser = userService.getUserById(jwtUtil.extractUser(token).getId());
            Photo photo = photoService.getPhotoById(photoId);
            if(photo.getOwner().getId() == existingUser.getId()){
                photoService.deletePhotosById(photoId);
                return  ResponseEntity.ok().body("Photo deleted successfully");
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are note Owner of this photo");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("albumId") Album albumId,

                                              HttpServletRequest request) {


            String token = extractTokenFromRequest(request);

            if (token != null) {
                User existingUser = userService.getUserById(jwtUtil.extractUser(token).getId());
                try {

                    LocalDateTime date = LocalDateTime.now();
                String currentDirectory = System.getProperty("user.dir");

                String photoDirPath = currentDirectory + File.separator + "photosData";
                File photoDir = new File(photoDirPath);
                if (!photoDir.exists()) {
                    photoDir.mkdirs();
                }


                String ownerIdDirPath = photoDirPath + File.separator + existingUser.getNom();
                File ownerIdDir = new File(ownerIdDirPath);
                if (!ownerIdDir.exists()) {
                    ownerIdDir.mkdirs();
                }



                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(ownerIdDirPath, fileName);
                file.transferTo(filePath.toFile());


                Photo photo = new Photo(fileName, filePath.toString(), description, date, albumId, existingUser);
                photoService.savePhoto(photo);

                return ResponseEntity.ok().body("Photo uploaded successfully");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
            }
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
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



    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

}