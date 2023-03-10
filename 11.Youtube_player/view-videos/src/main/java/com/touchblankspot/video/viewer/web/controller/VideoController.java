package com.touchblankspot.video.viewer.web.controller;

import com.touchblankspot.video.viewer.service.VideoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class VideoController {

    @NonNull
    private final VideoService videoService;

    @GetMapping(value = "/reloadLatest")
    public ResponseEntity<String> refreshVideos() {
        videoService.reloadLatestVideos();
        return ResponseEntity.ok("Video reloaded");
    }

    @GetMapping("/watchUrls")
    public ResponseEntity<List<String>> getWatchUrls() {
        return ResponseEntity.ok(videoService.getWatchUrls());
    }

    @GetMapping("/embeddedUrls")
    public ResponseEntity<List<String>> getEmbeddedUrls() {
        return ResponseEntity.ok(videoService.getWatchUrls());
    }

    @GetMapping("/durations")
    public ResponseEntity<Map<String,Long>> getVideoIdAndDurations(){
        return ResponseEntity.ok(videoService.getVideoIdAndDurations());
    }
}
