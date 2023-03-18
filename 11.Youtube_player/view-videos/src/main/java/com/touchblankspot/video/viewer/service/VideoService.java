package com.touchblankspot.video.viewer.service;

import com.touchblankspot.video.viewer.type.Video;
import com.touchblankspot.video.viewer.util.OrderedProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
    private final YoutubeService youtubeService;

    private final String dataFileName = "YoutubeData.properties";

    private final String WATCH_URL_FORMAT = "https://www.youtube.com/watch?v=%s";

    private final Path FILE_PATH = Path.of(dataFileName);

    private OrderedProperties properties = new OrderedProperties();

    private final Integer[] durationLimits = {1200000, 1800000};

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

    public Map<String, String> getWatchVideoDetails(String videoId, Integer recentLimit, Integer pageSize, Integer pageNo) {
        Map<String, String> videoDetails = new HashMap<>();
        if (StringUtils.hasText(videoId) && recentLimit < 1) {
            videoDetails.putAll(getWatchVideoDetailById(videoId));
        } else if (recentLimit > 0) {
            videoDetails.putAll(getWatchVideoDetails(recentLimit));
        } else {
            OrderedProperties properties = loadData();
            int videoSize = properties.size();
            int startLimit = pageNo * pageSize;
            startLimit = Math.max(startLimit, 0);
            int endLimit = (pageNo + 1) * pageSize;
            endLimit = Math.min(endLimit, videoSize);
            new LinkedList<>(properties.stringPropertyNames()).subList(startLimit, endLimit).forEach(key ->
                    videoDetails.put(String.format(WATCH_URL_FORMAT, key), properties.getProperty(key)));
        }
        return videoDetails;
    }


    public Map<String, String> getVideoIdAndDurations() {
        OrderedProperties properties = loadData();
        return properties.stringPropertyNames().stream().collect(Collectors.toMap(key -> key, properties::getProperty));
    }

    public int reloadLatestVideos() {
        properties = writeProperties(true);
        return Objects.isNull(properties) ? 0 : properties.size();
    }

    public int getVideoCount() {
        return loadData().size();
    }

    public List<List<Video>> constructVideoWithLimit() {
        List<List<Video>> result = new ArrayList<>();
        long currentDuration = 0L;
        List<Video> currentData = new ArrayList<>();
        List<Video> videos = constructVideoDetails();
        for (int index = 0; index < videos.size(); index++) {
            Video video = videos.get(index);
            currentDuration += video.getDuration();
            currentData.add(video);
            if (currentDuration >= 900000 && currentDuration <= 1200000) {
                result.add(currentData);
                currentData = new ArrayList<>();
                currentDuration = 0L;
            }
            if (index == (videos.size() - 1) && currentDuration > 0) {
                result.add(currentData);
            }
        }
        return result;
    }

    private List<Video> constructVideoDetails() {
        return loadData().entrySet().stream().map(entry -> Video.builder().videoId(entry.getKey()).duration(Long.valueOf(entry.getValue())).build())
                .toList();
    }


    private OrderedProperties loadData() {
        if (!properties.isEmpty()) {
            return properties;
        }
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
        OrderedProperties properties = youtubeService.loadVideoDetails();
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
