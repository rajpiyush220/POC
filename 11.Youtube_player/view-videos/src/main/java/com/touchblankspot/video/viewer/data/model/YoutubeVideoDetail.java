package com.touchblankspot.video.viewer.data.model;

import com.touchblankspot.video.viewer.common.data.model.embedded.Mutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "youtube_video_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeVideoDetail  extends Mutable {

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "is_shorts")
    private Boolean isShorts = false;

    @Column(name = "embedded_url")
    private String embeddedUrl;
}
