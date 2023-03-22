package com.touchblankspot.youtube.player.web.rest.controller;

import com.touchblankspot.youtube.player.data.model.YoutubeVideoDetail;
import com.touchblankspot.youtube.player.service.YoutubeService;
import com.touchblankspot.youtube.player.web.type.PulledVideoResponse;
import com.touchblankspot.youtube.player.web.type.YoutubeVideoDetailMapper;
import com.touchblankspot.youtube.player.web.type.YoutubeVideoDetailResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class YouTubeController {

    @NonNull
    private final YoutubeService youtubeService;

    @NonNull
    private final YoutubeVideoDetailMapper mapper;

    @GetMapping(value = "/initialize", produces = "application/json")
    public ResponseEntity<List<PulledVideoResponse>> initializeVideos() {
        return ResponseEntity.ok(youtubeService.initializeDatabase());
    }

    @GetMapping(value = "/pullLatest", produces = "application/json")
    public ResponseEntity<String> PullLatestVideos() {
        int videoCount = youtubeService.PullLatestVideos();
        return ResponseEntity.ok(videoCount + " video updated.");
    }

    @GetMapping(value = "/videoCountByPublishDate", produces = "application/json")
    public ResponseEntity<List<PulledVideoResponse>> videoDetails() {
        return ResponseEntity.ok(youtubeService.getVideoCountByPublishDate());
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public ResponseEntity<List<YoutubeVideoDetailResponse>> getAll() {
        return ResponseEntity.ok(youtubeService.findAll().stream().map(video -> mapper.toApi(video)).toList());
    }

}
