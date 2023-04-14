package com.touchblankspot.youtube.player.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "youtube")
@Getter
@Setter
public class YoutubeProperties {

	private Api api;

	private Map<String, Channel> channels;

	@Getter
	@Setter
	public static class Api {

		private String part;

		private String order;

		private String type;

		private String durationPart;

		private Integer maxResults;

	}

	@Getter
	@Setter
	public static class Channel {

		private String key;

		private String channelId;

		private String name;

	}

}
