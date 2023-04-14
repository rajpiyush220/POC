package com.touchblankspot.youtube.player.client.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "kind", "etag" })
public class DurationItem {

	private String kind;

	private String etag;

	private String id;

	private ContentDetails contentDetails;

	private Player player;

	private DurationSnippet snippet;

}
