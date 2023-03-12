package com.touchblankspot.video.viewer.service;

import com.touchblankspot.video.viewer.client.LatestVideoClient;
import com.touchblankspot.video.viewer.client.types.Item;
import com.touchblankspot.video.viewer.client.types.VideoDurationResponse;
import com.touchblankspot.video.viewer.client.types.VideoResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

    private final String WATCH_URL_FORMAT = "https://www.youtube.com/watch?v=%s&ab_channel=MesmerizingNatureVlogs";

    private final Path FILE_PATH = Path.of(dataFileName);

    public Set<String> getVideoIds() {
        return loadData().keySet();
    }

    public List<String> getWatchUrls() {
        return getVideoIds().stream().map(id -> String.format(WATCH_URL_FORMAT, id)).toList();
    }

    public Map<String, Long> getWatchVideoDetails() {
        Map<String, Long> videoData =  loadData();
        return videoData.keySet().stream().collect(Collectors.toMap(key -> String.format(WATCH_URL_FORMAT, key),
                videoData::get,
                (e1, e2) -> e1,
                LinkedHashMap::new));
    }

    public Map<String, Long> get20RecentVideoDetails() {
        Map<String, Long> videoData =  loadData();
        return videoData.keySet().stream().limit(20).collect(Collectors.toMap(key -> String.format(WATCH_URL_FORMAT, key),
                videoData::get,
                (e1, e2) -> e1,
                LinkedHashMap::new));
    }

    public Map<String, Long> getVideoIdAndDurations() {
        return loadData();
    }

    public void reloadLatestVideos() {
        writeProperties(true);
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

    private Map<String, Long> loadData() {
        Properties properties = new Properties();
        try {
            if (!Files.exists(FILE_PATH)) {
                return writeProperties(false);
            }
            properties.load(Files.newInputStream(FILE_PATH));
        } catch (IOException exception) {
            log.error("Unable to load data", exception);
        }
        return constructMap(properties);
    }

    private Map<String, Long> writeProperties(Boolean isCleanRequired) {
        Properties properties;
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
        properties = new Properties();
        VideoResponse videoResponse = videoClient.search(apiKey, channelId, part, order, type,maxResults);
        videoResponse.getItems().stream().map(Item::getId).filter(id -> id.getKind().equals(VIDEO_FILTER))
                .forEach(id -> properties.put(id.getVideoId(), getDuration(id.getVideoId())));
        try {
            // delete file if exists
            Files.deleteIfExists(FILE_PATH);
            properties.store(new FileOutputStream(dataFileName), null);
        } catch (IOException e) {
            log.error("Unable to write file", e);
        }
        return constructMap(properties);
    }

    private Map<String, Long> constructMap(Properties properties) {
        Map<String, Long> map = new LinkedHashMap<>();
        if (properties != null && !properties.isEmpty()) {
            properties.stringPropertyNames().forEach(key -> map.put(key, Long.valueOf(properties.get(key).toString())));
        }
        return map;
    }
}
