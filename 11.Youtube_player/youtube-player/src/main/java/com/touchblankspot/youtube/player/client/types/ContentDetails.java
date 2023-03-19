package com.touchblankspot.youtube.player.client.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"dimension", "definition", "caption", "licensedContent", "contentRating", "projection"})
public class ContentDetails {
    private String duration;
    private String dimension;
    private String definition;
    private String caption;
    private boolean licensedContent;
    private ContentRating contentRating;
    private String projection;
}
