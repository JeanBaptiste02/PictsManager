package com.epitech.pictsmanager.form;

import com.epitech.pictsmanager.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoForm {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("path")
    private String path;
    @JsonProperty("description")
    private String description;
    @JsonProperty("date")
    private LocalDateTime date;
    @JsonProperty("album_id")
    private Long album_id;
    @JsonProperty("owner_id")
    private User owner_id;
    @JsonProperty("image")
    private byte[] image;
}
