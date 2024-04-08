package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    @GetMapping("/getPhotos")
    public List<Photo> getPhotos(){
        return photoService.getPhotos();
    }

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
            String currentDirectory = System.getProperty("user.dir");
            String albumIdDirPath = getString(albumId, ownerId, currentDirectory);
            File albumIdDir = new File(albumIdDirPath);
            if (!albumIdDir.exists()) {
                albumIdDir.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            String compressedFileName = compressAndSaveImage(file, albumIdDirPath, fileName);

            String filePath = Paths.get(albumIdDirPath, compressedFileName).toString();

            Photo photo = new Photo(compressedFileName, filePath, description, date, albumId, ownerId);
            photoService.savePhoto(photo);

            return ResponseEntity.ok().body("Photo uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
        }
    }

    private String compressAndSaveImage(MultipartFile file, String albumIdDirPath, String fileName) throws IOException {
        File compressedFile = new File(albumIdDirPath, "compressed_" + fileName); //nouveau nom du fichier compressé
        BufferedImage image = ImageIO.read(file.getInputStream()); //lit l'image

        ImageWriteParam param = new JPEGImageWriteParam(null); // img qualité
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.2f); // qualité de 0.0 à 1.0

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next(); // ImageWriter au format jpg

        // flux de sorti img compressé
        try (OutputStream os = new FileOutputStream(compressedFile)) {
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);
            // making the image
            writer.write(null, new IIOImage(image, null, null), param);
            //flux end
            ios.close();
            writer.dispose();
        }
        return compressedFile.getName();
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