package com.touchblankspot.video.viewer.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Id {
    private String kind;
    private String videoId;
    private String playlistId;
    private String channelId;
}
