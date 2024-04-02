package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.dtos.PhotoDTO;
import com.epitech.pictsmanager.Responses.GenericResponse;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import com.epitech.pictsmanager.repositories.PhotoRepository;
import com.epitech.pictsmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.SQLException;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Blob;
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

}
