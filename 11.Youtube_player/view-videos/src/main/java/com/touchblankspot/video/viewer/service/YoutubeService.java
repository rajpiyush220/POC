package com.touchblankspot.video.viewer.service;

import com.touchblankspot.video.viewer.client.LatestVideoClient;
import com.touchblankspot.video.viewer.client.types.Item;
import com.touchblankspot.video.viewer.client.types.VideoDurationResponse;
import com.touchblankspot.video.viewer.client.types.VideoResponse;
import com.touchblankspot.video.viewer.util.OrderedProperties;
import com.touchblankspot.video.viewer.util.YoutubeProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class YoutubeService {

    @NonNull
    private final LatestVideoClient videoClient;

    @NonNull
    private final YoutubeProperties youtubeProperties;

    private final Random random = new Random();

    private final String VIDEO_FILTER = "youtube#video";

    public OrderedProperties loadVideoDetails() {
        YoutubeProperties.Api api = youtubeProperties.getApi();
        OrderedProperties properties = new OrderedProperties();
        buildVideoProperties(youtubeProperties.getChannels().get("box"), api, properties);
        buildVideoProperties(youtubeProperties.getChannels().get("world"), api, properties);
        return properties;
    }


    private void buildVideoProperties(YoutubeProperties.Channel channel, YoutubeProperties.Api api,
                                      OrderedProperties properties) {
        VideoResponse videoResponse = videoClient.search(channel.getKey(), channel.getChannelId(), api.getPart(), api.getOrder(), api.getType(), api.getMaxResults());
        videoResponse.getItems().stream().map(Item::getId).filter(id -> id.getKind().equals(VIDEO_FILTER))
                .forEach(id -> properties.setProperty(id.getVideoId(), getDuration(id.getVideoId(), channel.getKey())));
    }

    private String getDuration(String videoId, String apiKey) {
        VideoDurationResponse durationResponse = videoClient.getVideoDuration(videoId, apiKey, youtubeProperties.getApi().getDurationPart());
        String durationString = durationResponse.getItems().stream().map(item -> item.getContentDetails().getDuration()).findFirst().orElse("");
        long duration = 0L;
        if (durationString.length() > 0) {
            Duration d = Duration.parse(durationString);
            duration = d.get(java.time.temporal.ChronoUnit.SECONDS);
            duration = addAdditionalTime(duration) + (duration * 1000);
        }
        return String.valueOf(duration);
    }

    private long addAdditionalTime(long videoDuration) {
        long baseDuration = (videoDuration < 100 ? videoDuration : 2) * 1000;
        return baseDuration + ((random.nextInt(50 - 29) + 20) * 1000);
    }
}
