package com.touchblankspot.youtube.player.data.model;

import com.touchblankspot.youtube.player.data.model.embedded.Mutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "video_details")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class YoutubeVideoDetail extends Mutable {

	@NonNull
	@Column(name = "channel_id")
	private String channelId;

	@NonNull
	@Column(name = "channel_name")
	private String channelName;

	@NonNull
	@Column(name = "video_id")
	private String videoId;

	@NonNull
	@Column(name = "duration")
	private Long duration;

	@NonNull
	@Column(name = "publish_date")
	private LocalDate publishDate;

	@NonNull
	@Column(name = "is_shorts")
	private Boolean isShorts;

	@NonNull
	@Column(name = "embedded_url")
	private String embeddedUrl;

}
