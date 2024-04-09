package com.epitech.pictsmanager.service;


import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.PhotoRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Photo> getPhotos(){
        return photoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Photo> getPhotosByUserIdAndAlbumId(Long ownerId, Long albumId){
        return photoRepository.findByOwner_IdAndAlbum_Id(ownerId, albumId);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    public List<String> getPhotoPathsByUserId(long userId) {
        return photoRepository.findPhotoPathsByOwner_id(userId);
    }

    public byte[] combineImages(List<String> photoPaths) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (String path : photoPaths) {
            byte[] imageBytes = Files.readAllBytes(Paths.get(path));
            baos.write(imageBytes);
        }
        return baos.toByteArray();
    }

    public String compressAndSaveImage(MultipartFile file, String albumIdDirPath, String fileName) throws IOException {
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

}