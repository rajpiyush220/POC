package com.touchblankspot.youtube.player.web.type;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class PulledVideoResponse {

    @NonNull
    private String publishDate;

    @NonNull
    private Integer videoCount;
}
