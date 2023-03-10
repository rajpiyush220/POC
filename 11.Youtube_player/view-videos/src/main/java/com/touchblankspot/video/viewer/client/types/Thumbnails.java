package com.touchblankspot.video.viewer.client.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thumbnails {
    @JsonProperty("default")
    private Default mydefault;

    @JsonProperty("medium")
    private Default medium;

    @JsonProperty("high")
    private Default high;
}
