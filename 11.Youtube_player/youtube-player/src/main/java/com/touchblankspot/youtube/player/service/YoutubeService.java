package com.touchblankspot.youtube.player.service;

import com.touchblankspot.youtube.player.client.YouTubeVideoFeignClient;
import com.touchblankspot.youtube.player.client.types.DurationItem;
import com.touchblankspot.youtube.player.client.types.Item;
import com.touchblankspot.youtube.player.client.types.VideoDurationResponse;
import com.touchblankspot.youtube.player.client.types.VideoResponse;
import com.touchblankspot.youtube.player.data.model.YoutubeVideoDetail;
import com.touchblankspot.youtube.player.data.repository.YoutubeRepository;
import com.touchblankspot.youtube.player.util.YoutubeProperties;
import com.touchblankspot.youtube.player.web.type.PulledVideoResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class YoutubeService {

    @NonNull
    private final YoutubeRepository repository;

    @NonNull
    private final YouTubeVideoFeignClient videoFeignClient;

    @NonNull
    private final YoutubeProperties youtubeProperties;

    private final String VIDEO_FILTER = "youtube#video";

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

    private final String WATCH_URL_FORMAT = "https://www.youtube.com/watch?v=%s";

    private final Random random = new Random();


    public List<PulledVideoResponse> getVideoCountByPublishDate() {
        return repository.getVideoCountByPublishDate().stream().map(object -> new PulledVideoResponse(object[0].toString(), Integer.parseInt(object[1].toString()))).toList();
    }

    public Map<String, String> getVideoPlayDetails(Boolean isShorts) {
        return repository.findByIsShorts(isShorts).stream().collect(Collectors.toMap(video -> String.format(WATCH_URL_FORMAT, video.getVideoId()),
                video -> String.valueOf(addAdditionalTime(video.getDuration()))));
    }

    public Map<String, String> getVideoDetails() {
        return repository.findAll().stream().collect(Collectors.toMap(video -> String.format(WATCH_URL_FORMAT, video.getVideoId()),
                video -> String.valueOf(addAdditionalTime(video.getDuration()))));
    }

    public List<PulledVideoResponse> initializeDatabase() {
        List<YoutubeVideoDetail> videoDetails =
                buildVideoDetailsByChannel(null);
        log.info("Video Details {}", videoDetails);
        repository.saveAll(videoDetails);
        log.info("Database initialized successfully.");
        return getVideoCountByPublishDate();
    }

    public int storeDailyVideo(LocalDate executionDate) {
        log.info("Storing videos for {}", executionDate);
        List<YoutubeVideoDetail> videoDetails = buildVideoDetailsByChannel(executionDate);
        log.info("Video Details {}", videoDetails);
        repository.saveAll(videoDetails);
        log.info("Stored video count {}", videoDetails.size());
        return videoDetails.size();
    }

    private List<YoutubeVideoDetail> buildVideoDetailsByChannel(LocalDate executionDate) {
        return youtubeProperties.getChannels().keySet().stream().map(key -> buildVideoDetails(key, executionDate)).flatMap(List::stream).toList();
    }

    private List<YoutubeVideoDetail> buildVideoDetails(String key, LocalDate searchAfter) {
        YoutubeProperties.Api api = youtubeProperties.getApi();
        YoutubeProperties.Channel channel = youtubeProperties.getChannels().get(key);
        VideoResponse videoResponse = getYouTubeVideos(channel.getKey(), channel.getChannelId(), api.getPart(), api.getOrder(), api.getType(),
                api.getMaxResults(), searchAfter);
        String apiKey = channel.getKey();
        String contentPart = youtubeProperties.getApi().getDurationPart();
        String channelId = channel.getChannelId();
        String channelName = channel.getName();
        return videoResponse.getItems().stream().map(Item::getId).filter(id -> id.getKind().equals(VIDEO_FILTER))
                .map(responseId -> {
                    String videoId = responseId.getVideoId();
                    long duration = 0L;
                    LocalDate publishDate = LocalDate.now();
                    boolean isShorts = false;
                    String embeddedUrl = "";
                    VideoDurationResponse durationResponse = videoFeignClient.getVideoDuration(videoId, apiKey, contentPart);
                    Optional<DurationItem> optionalDurationItem = durationResponse.getItems().stream().findFirst();
                    if (optionalDurationItem.isPresent()) {
                        DurationItem item = optionalDurationItem.get();
                        duration = parseDuration(item.getContentDetails().getDuration());
                        isShorts = (duration <= 60L);
                        embeddedUrl = item.getPlayer().getEmbedHtml();
                        Date date = item.getSnippet().getPublishedAt();
                        publishDate = Instant.ofEpochMilli(date.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    }
                    return new YoutubeVideoDetail(channelId, channelName, videoId, duration, publishDate, isShorts, embeddedUrl);
                }).toList();
    }

    private VideoResponse getYouTubeVideos(String key, String channelId, String part, String order, String type, Integer maxResults, LocalDate publishedAfter) {
        return publishedAfter == null ?
                videoFeignClient.search(key, channelId, part, order, type, maxResults) :
                videoFeignClient.searchAfterDate(key, channelId, part, LocalDateTime.of(publishedAfter, LocalTime.MIDNIGHT).format(formatter), type);
    }

    private long parseDuration(String durationString) {
        long duration = 0L;
        if (durationString.length() > 0) {
            Duration d = Duration.parse(durationString);
            duration = d.get(java.time.temporal.ChronoUnit.SECONDS);
        }
        return duration;
    }

    private long addAdditionalTime(long videoDuration) {
        return (videoDuration + (videoDuration < 100 ? videoDuration : 120)) * 1000;
    }
}
