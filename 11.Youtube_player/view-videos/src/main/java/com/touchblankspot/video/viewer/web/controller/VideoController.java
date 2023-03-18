package com.touchblankspot.video.viewer.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touchblankspot.video.viewer.service.VideoService;
import com.touchblankspot.video.viewer.type.Video;
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

    @NonNull
    private final ObjectMapper objectMapper;


    @GetMapping(value = "/reloadLatest")
    public ResponseEntity<String> refreshVideos() {
        int count = videoService.reloadLatestVideos();
        return ResponseEntity.ok(count + " Video reloaded.");
    }

    @GetMapping(value = "/limits")
    public ResponseEntity<String> limits() {
        List<List<Video>> lists = videoService.constructVideoWithLimit();
        return ResponseEntity.ok(toJson(lists));
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse map to json");
            return "";
        }
    }


}
