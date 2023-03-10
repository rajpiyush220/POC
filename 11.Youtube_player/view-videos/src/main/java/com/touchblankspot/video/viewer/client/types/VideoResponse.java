package com.touchblankspot.video.viewer.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponse {

    private String kind;
    private String etag;
    private String regionCode;
    private PageInfo pageInfo;
    private List<Item> items;

}


