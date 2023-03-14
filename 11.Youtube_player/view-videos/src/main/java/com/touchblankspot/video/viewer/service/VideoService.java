package com.touchblankspot.video.viewer.service;

import com.touchblankspot.video.viewer.client.LatestVideoClient;
import com.touchblankspot.video.viewer.client.types.Item;
import com.touchblankspot.video.viewer.client.types.VideoDurationResponse;
import com.touchblankspot.video.viewer.client.types.VideoResponse;
import com.touchblankspot.video.viewer.util.OrderedProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class VideoService {

    @NonNull
    private final LatestVideoClient videoClient;

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.channelId}")
    private String channelId;

    @Value("${youtube.api.part}")
    private String part;

    @Value("${youtube.api.order}")
    private String order;

    @Value("${youtube.api.type}")
    private String type;

    @Value("${youtube.api.result.maxResults:50}")
    private Integer maxResults;

    @Value("${youtube.api.duration.part:contentDetails}")
    private String durationPart;

    private final String dataFileName = "YoutubeData.properties";

    private final String VIDEO_FILTER = "youtube#video";

    private final String WATCH_URL_FORMAT = "https://www.youtube.com/watch?v=%s";

    private final Path FILE_PATH = Path.of(dataFileName);

    public Set<String> getVideoIds() {
        return loadData().stringPropertyNames();
    }

    public List<String> getWatchUrls() {
        return getVideoIds().stream().map(id -> String.format(WATCH_URL_FORMAT, id)).toList();
    }

    public Map<String, String> getWatchVideoDetails(Integer limit) {
        OrderedProperties properties = loadData();
        int finalLimit = (limit > 0 && limit < properties.size()) ? limit : properties.size();
        return new ArrayList<>(properties.entrySet()).subList(0, finalLimit).stream().collect(
                Collectors.toMap(entry -> String.format(WATCH_URL_FORMAT, entry.getKey()), Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<String, String> getVideoDetailsByLength(long largeVideoMinLength, Boolean isSmallRequired) {
        OrderedProperties properties = loadData();
        return properties.entrySet().stream().filter(entry ->
                        (isSmallRequired && Long.parseLong(entry.getValue()) < largeVideoMinLength) ||
                                (!isSmallRequired && Long.parseLong(entry.getValue()) > largeVideoMinLength)
                )
                .collect(
                        Collectors.toMap(entry -> String.format(WATCH_URL_FORMAT, entry.getKey()), Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
    }

    public Map<String, String> getWatchVideoDetailById(String videoId) {
        OrderedProperties properties = loadData();
        if (properties.containsProperty(videoId)) {
            return Map.of(String.format(WATCH_URL_FORMAT, videoId), properties.getProperty(videoId));
        }
        return Map.of();
    }

    public Map<String, String> getWatchVideoDetails(String videoId, Integer recentLimit) {
        return (StringUtils.hasText(videoId) && recentLimit < 1) ?
                getWatchVideoDetailById(videoId) : getWatchVideoDetails(recentLimit);
    }


    public Map<String, String> getVideoIdAndDurations() {
        OrderedProperties properties = loadData();
        return properties.stringPropertyNames().stream().collect(Collectors.toMap(key -> key, properties::getProperty));
    }

    public int reloadLatestVideos() {
        OrderedProperties properties = writeProperties(true);
        return Objects.isNull(properties) ? 0 : properties.size();
    }

    private String getDuration(String videoId) {
        VideoDurationResponse durationResponse = videoClient.getVideoDuration(videoId, apiKey, durationPart);
        String durationString = durationResponse.getItems().stream().map(item -> item.getContentDetails().getDuration()).findFirst().orElse("");
        long duration = 0L;
        if (durationString.length() > 0) {
            Duration d = Duration.parse(durationString);
            duration = d.get(java.time.temporal.ChronoUnit.SECONDS) * 1000;
            // adding 10 sec load time
            duration = duration + 10 * 1000;
        }
        return String.valueOf(duration);
    }

    private OrderedProperties loadData() {
        OrderedProperties properties = new OrderedProperties();
        try {
            if (!Files.exists(FILE_PATH)) {
                return writeProperties(false);
            }
            properties.load(Files.newInputStream(FILE_PATH));
        } catch (IOException exception) {
            log.error("Unable to load data", exception);
        }
        return properties;
    }

    private OrderedProperties writeProperties(Boolean isCleanRequired) {
        OrderedProperties properties;
        if (isCleanRequired) {
            try {
                // delete file if exists
                Files.deleteIfExists(FILE_PATH);
            } catch (IOException e) {
                log.error("Unable to write file", e);
            }
        }
        if (Files.exists(FILE_PATH)) {
            return null;
        }
        properties = new OrderedProperties();
        VideoResponse videoResponse = videoClient.search(apiKey, channelId, part, order, type, maxResults);

        videoResponse.getItems().stream().map(Item::getId).filter(id -> id.getKind().equals(VIDEO_FILTER))
                .forEach(id -> properties.setProperty(id.getVideoId(), getDuration(id.getVideoId())));
        try {
            // delete file if exists
            Files.deleteIfExists(FILE_PATH);
            properties.store(new FileOutputStream(dataFileName), null);
        } catch (IOException e) {
            log.error("Unable to write file", e);
        }
        return properties;
    }
}
