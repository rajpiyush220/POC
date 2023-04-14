package com.touchblankspot.youtube.player.client.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private String kind;

	private String etag;

	private Id id;

	private Snippet snippet;

}
