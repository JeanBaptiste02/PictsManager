package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping("getPhotos")
    public List<Photo> getPhotos(){
        return photoService.getPhotos();
    }
}
