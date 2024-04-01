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

    //////////////////////////////

    private Blob convertMultipartFileToBlob(MultipartFile multipartFile) throws IOException, SQLException {
        Session session = entityManager.unwrap(Session.class);
        Blob blob = Hibernate.getLobCreator(session).createBlob(multipartFile.getInputStream(), multipartFile.getSize());
        return blob;
    }

    public Object upload(String id, MultipartFile image, String description) throws IOException {
        try {
            User user = userRepository.findUserById(Long.parseLong(id));

            // Ensure the image is not null
            if (image == null || image.isEmpty()) {
                return ResponseEntity.badRequest().body(new GenericResponse("Image file is empty", HttpStatus.BAD_REQUEST.value()));
            }

            // Convert MultipartFile to Blob
            Blob imageData = convertMultipartFileToBlob(image);

            // Create a new Photo entity
            Photo dbImage = new Photo();
            dbImage.setImage(imageData);
            dbImage.setDescription(description);
            dbImage.setName(image.getOriginalFilename());
            dbImage.setOwner_id(user);

            // Save the entity
            photoRepository.save(dbImage);

            return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("Image uploaded successfully", HttpStatus.CREATED.value()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse("Error while uploading image", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

}
