package com.touchblankspot.youtube.player.web.rest.controller;

import com.touchblankspot.youtube.player.service.YoutubeService;
import com.touchblankspot.youtube.player.web.type.PulledVideoResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class YouTubeController {

    @NonNull
    private final YoutubeService youtubeService;

    @GetMapping("/initialize")
    public ResponseEntity<List<PulledVideoResponse>> initializeVideos() {
        return ResponseEntity.ok(youtubeService.initializeDatabase());
    }

    @GetMapping("/pullLatest")
    public ResponseEntity<String> PullLatestVideos() {
        int videoCount = youtubeService.storeDailyVideo(LocalDate.now(ZoneId.of("Asia/Kolkata")).minusDays(1));
        return ResponseEntity.ok(videoCount+" video updated.");
    }

    @GetMapping("/videoDetails")
    public ResponseEntity<List<PulledVideoResponse>> videoDetails() {
        return ResponseEntity.ok(youtubeService.getVideoCountByPublishDate());
    }

}
