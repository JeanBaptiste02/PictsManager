package com.epitech.pictsmanager.controllers;

import com.epitech.pictsmanager.dtos.PhotoDTO;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.form.PhotoForm;
import com.epitech.pictsmanager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Photo savePhoto(@RequestBody PhotoForm photoForm){
        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setName(photoForm.getName());
        photoDTO.setPath(photoForm.getPath());
        photoDTO.setDescription(photoForm.getDescription());
        photoDTO.setDate(photoForm.getDate());
        photoDTO.setAlbum_id(photoForm.getAlbum_id());
        photoDTO.setOwner_id(photoForm.getOwner_id());

        return photoService.save(photoDTO, photoForm.getPath());
    }
}
