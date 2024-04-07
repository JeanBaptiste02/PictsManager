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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping("/getPhotos")
    public List<Photo> getPhotos(){
        return photoService.getPhotos();
    }


//     @GetMapping("/getPhotos/{userId}")
// public ResponseEntity<byte[]> getPhotosByUserId(@PathVariable Long userId) {
//     List<String> photoPaths = photoService.getPhotoPathsByUserId(userId);
//     if (photoPaths.isEmpty()) {
//         return ResponseEntity.noContent().build(); // Si l'utilisateur n'a aucune photo
//     } else {
//         // Charger les images à partir des chemins d'accès et les renvoyer dans la réponse HTTP
//         try {
//             byte[] combinedImageBytes = photoService.combineImages(photoPaths);
//             HttpHeaders headers = new HttpHeaders();
//             headers.setContentType(MediaType.IMAGE_JPEG);
//             headers.setContentLength(combinedImageBytes.length);
//             return new ResponseEntity<>(combinedImageBytes, headers, HttpStatus.OK);
//         } catch (IOException e) {
//             e.printStackTrace();
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // En cas d'erreur lors du chargement des images
//         }
//     }
// }


@GetMapping("/photos/user/{userId}")
public List<String> getPhotosByUserId(@PathVariable Long userId) {
    return photoService.getPhotoPathsByUserId(userId);
}




    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("albumId") Long albumId,
                                              @RequestParam("ownerId") User ownerId) {
        try {
            
            
            


            LocalDateTime date = LocalDateTime.now();
            // Récupérer le répertoire courant
            String currentDirectory = System.getProperty("user.dir");

            // Créer le répertoire pour les photos dans le répertoire courant s'il n'existe pas
            String photoDirPath = currentDirectory + File.separator + "photosData";
            File photoDir = new File(photoDirPath);
            if (!photoDir.exists()) {
                photoDir.mkdirs();
            }

            // Créer le répertoire pour ownerId s'il n'existe pas
            String ownerIdDirPath = photoDirPath + File.separator + ownerId.getNom();
            File ownerIdDir = new File(ownerIdDirPath);
            if (!ownerIdDir.exists()) {
                ownerIdDir.mkdirs();
            }

            // Créer le répertoire pour albumId s'il n'existe pas
            String albumIdDirPath = ownerIdDirPath + File.separator + albumId;
            File albumIdDir = new File(albumIdDirPath);
            if (!albumIdDir.exists()) {
                albumIdDir.mkdirs();
            }

            // Enregistrer le fichier de photo dans le répertoire
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(albumIdDirPath, fileName);
            file.transferTo(filePath.toFile());


            // Insérer les détails de la photo dans la base de données
            Photo photo = new Photo(fileName, filePath.toString(), description, date, albumId, ownerId);
            photoService.savePhoto(photo);

            return ResponseEntity.ok().body("Photo uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
        }
    }

   
}
