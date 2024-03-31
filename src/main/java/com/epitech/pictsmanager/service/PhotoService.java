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

    public Photo save(PhotoDTO photoDTO, String path){
        try{
            if(photoRepository.findPhotoByPath(path) != null){
                throw new RuntimeException("La photo existe deja");
            }
            if(photoDTO.getName() == null || photoDTO.getName().isEmpty()){
                throw new RuntimeException("Le nom de l'image est vide");
            }

            Photo photo = mapToPhotoEntity(photoDTO);
            return photoRepository.save(photo);

        }catch(Exception e){
            throw new RuntimeException("Erreur lors du sauvegarde de la photo");
        }

    }

    private Photo mapToPhotoEntity(PhotoDTO photoDTO){
        Photo photoEntity = new Photo();
        photoEntity.setId(photoDTO.getId());
        photoEntity.setName(photoDTO.getName());
        photoEntity.setPath(photoDTO.getPath());
        photoEntity.setDescription(photoDTO.getDescription());
        photoEntity.setDate(photoDTO.getDate());
        photoEntity.setAlbum_id(photoDTO.getAlbum_id());
        photoEntity.setOwner_id(photoDTO.getOwner_id());

        return photoEntity;
    }
}
