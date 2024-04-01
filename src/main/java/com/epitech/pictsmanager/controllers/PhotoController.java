package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.PhotoDTO;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.form.PhotoForm;
import com.epitech.pictsmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("addPhoto")
    public Object savePhoto(@RequestParam("id") String id,
                       @RequestParam("image") MultipartFile image,
                       @RequestParam("description") String description) throws IOException, IOException {
        return photoService.upload(id, image, description);
    }
}
