package com.epitech.pictsmanager.dtos;

import com.epitech.pictsmanager.entity.Album;
import com.epitech.pictsmanager.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing album information
 * @author Jean-Baptiste, Kamel, Victor, Mahdi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {

    private Long id;
    private String title;
    private User owner;

    /**
     * Constructs an AlbumDTO object
     * @param album The Album entity from which to construct the DTO
     */
    public AlbumDTO(String email, Long id) {
        this.id = id;
        this.owner = new User();
        this.owner.setEmail(email);
    }
}
