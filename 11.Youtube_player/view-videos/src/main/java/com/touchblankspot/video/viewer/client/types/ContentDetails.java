package com.touchblankspot.video.viewer.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDetails {
    private String duration;
    private String dimension;
    private String definition;
    private String caption;
    private boolean licensedContent;
    private ContentRating contentRating;
    private String projection;
}
