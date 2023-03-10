package com.touchblankspot.video.viewer.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private Integer totalResults;
    private Integer resultsPerPage;
}
