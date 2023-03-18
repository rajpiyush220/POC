package com.touchblankspot.youtube.player.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DurationSnippet {
    private Date publishedAt;
    private String channelId;
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String channelTitle;

    private String categoryId;

    private String liveBroadcastContent;
    private Localized localized;
}
