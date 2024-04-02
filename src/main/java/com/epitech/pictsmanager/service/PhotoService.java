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
import java.io.IOException;


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

    /////////////

    @Transactional
    public void savePhoto(MultipartFile file, String name, String path, String description, LocalDateTime date, Long album_id, User owner) throws IOException {
        byte[] imageData = file.getBytes();
        Photo photo = new Photo(name, path, description, date, album_id, owner, imageData);
        photoRepository.save(photo);
    }

    public byte[] getPhotoImage(Long photoId) {
        Photo photo = photoRepository.findById(photoId).orElse(null);
        if (photo != null) {
            return photo.getImage();
        }
        return null;
    }

}
