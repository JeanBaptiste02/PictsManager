package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getPhotos(){
        return photoRepository.findAll();
    }
}
