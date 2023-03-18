package com.touchblankspot.video.viewer.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class Video {

    @NonNull
    private String videoId;

    @NonNull
    private Long duration;
}
