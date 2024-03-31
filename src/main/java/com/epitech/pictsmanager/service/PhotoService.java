package com.epitech.pictsmanager.service;

import com.epitech.pictsmanager.dtos.PhotoDTO;
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

    public Boolean savePhoto(PhotoDTO photoDTO, String path){
        try{
            if(photoRepository.findPhotoByPath(path) == null){
                throw new RuntimeException("La photo n'existe pas");
            }else if(photoRepository.findPhotoByPath(path) != null){
                throw new RuntimeException("La photo existe deja");
            }
            if(photoDTO.getName() == null || photoDTO.getName().isEmpty()){
                throw new RuntimeException("Le nom de l'image est vide");
            }

        }catch(Exception e){
            throw new RuntimeException("Erreur lors du sauvegarde de la photo");
        }
    }
}
