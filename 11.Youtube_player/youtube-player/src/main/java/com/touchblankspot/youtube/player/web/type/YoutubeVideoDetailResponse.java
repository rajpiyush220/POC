package com.touchblankspot.youtube.player.web.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@JsonPropertyOrder({ "channelId", "channelName", "videoId", "duration", "publishDate", "isShorts", "embeddedUrl" })
@Data
@Builder
public class YoutubeVideoDetailResponse {

	@JsonProperty("channelId")
	private String channelId;

	@JsonProperty("channelName")
	private String channelName;

	@JsonProperty("videoId")
	private String videoId;

	@JsonProperty("duration")
	private Long duration;

	@JsonProperty("publishDate")
	private String publishDate;

	@JsonProperty("isShorts")
	private Boolean isShorts;

	@JsonProperty("embeddedUrl")
	private String embeddedUrl;

}
