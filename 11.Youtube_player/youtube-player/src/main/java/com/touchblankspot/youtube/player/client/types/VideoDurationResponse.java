package com.touchblankspot.youtube.player.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDurationResponse {

	private String kind;

	private String etag;

	private List<DurationItem> items;

	private PageInfo pageInfo;

}
