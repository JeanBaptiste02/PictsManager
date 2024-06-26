package com.epitech.pictsmanager.dtos;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.Photo;
import com.epitech.pictsmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing photo information
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

    private Long id;
    private String name;
    private String path;
    private String description;
    private LocalDateTime date;
    private Album album_id;
    private User owner_id;
    private Boolean visibility;

    /**
     * Constructs a PhotoDTO object from a Photo entity
     * @param photo The photo entity from which to construct the DTO
     */
    public PhotoDTO(final Photo photo){
        id = photo.getId();
        name = photo.getName();
        path = photo.getPath();
        description = photo.getDescription();
        date = photo.getDate();
        album_id = photo.getAlbum_id();
        owner_id = getOwner_id();
        visibility = photo.getVisibility();
    }
}