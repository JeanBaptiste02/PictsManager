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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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


}