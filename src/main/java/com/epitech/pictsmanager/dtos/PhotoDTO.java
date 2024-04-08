package com.epitech.pictsmanager.dtos;

import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

    private Long id;
    private String name;
    private String path;
    private String description;
    private LocalDateTime date;
    private Long album_id;
    private User owner_id;

    public PhotoDTO(final Photo photo){
        id = photo.getId();
        name = photo.getName();
        path = photo.getPath();
        description = photo.getDescription();
        date = photo.getDate();
        album_id = getAlbum_id();
        owner_id = getOwner_id();
    }
}