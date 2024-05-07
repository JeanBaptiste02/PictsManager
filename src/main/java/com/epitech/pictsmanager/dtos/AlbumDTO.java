package com.epitech.pictsmanager.dtos;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {

    private Long id;
    private String title;
    private User owner;

    public AlbumDTO(Album album) {
        id = album.getId();
        title = album.getTitle();
        owner = album.getOwner();
    }
}
