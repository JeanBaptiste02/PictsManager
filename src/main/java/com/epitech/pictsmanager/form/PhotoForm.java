package com.epitech.pictsmanager.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoForm {
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
    private Long owner_id;
}
