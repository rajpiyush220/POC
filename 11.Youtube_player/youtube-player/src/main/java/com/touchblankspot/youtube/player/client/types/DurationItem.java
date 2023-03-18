package com.touchblankspot.youtube.player.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DurationItem {
    private String kind;
    private String etag;
    private String id;
    private ContentDetails contentDetails;
    private Player player;
    private DurationSnippet snippet;
}

