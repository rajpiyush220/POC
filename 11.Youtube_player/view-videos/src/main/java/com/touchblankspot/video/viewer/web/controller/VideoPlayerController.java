package com.touchblankspot.video.viewer.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touchblankspot.video.viewer.service.VideoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/video/player")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class VideoPlayerController {

    @NonNull
    private final VideoService videoService;

    @NonNull
    private final ObjectMapper objectMapper;

    private final int maxDurationForViewCount = 45000;

    @GetMapping(value = {"/play", "/watchhour"})
    public String playSpecificVideo(@RequestParam(value = "videoId", defaultValue = "") String videoId,
                                    @RequestParam(value = "recentLimit", defaultValue = "0") Integer recentLimit,
                                    Model model) {
        Map<String, String> videoDetails = videoService.getWatchVideoDetails(videoId, recentLimit);
        model.addAttribute("jsonData", mapToJsonString(videoDetails));
        model.addAttribute("maxDuration", "0");
        model.addAttribute("title",  "Increase Watch Hour");
        return "playVideo";
    }

    @GetMapping(value = {"/count", "/viewcount"})
    public String increaseViewCountSpecificVideo(@RequestParam(value = "videoId", defaultValue = "") String videoId,
                                                 @RequestParam(value = "recentLimit", defaultValue = "0") Integer recentLimit,
                                                 Model model) {
        Map<String, String> videoDetails = videoService.getWatchVideoDetails(videoId, recentLimit);
        model.addAttribute("jsonData", mapToJsonString(videoDetails));
        model.addAttribute("maxDuration", maxDurationForViewCount);
        model.addAttribute("title", "Increase Watch Hour");
        return "playVideo";
    }

    @GetMapping(value = {"/start"})
    public String playVideos(@RequestParam(value = "tabCount", defaultValue = "1") Integer tabCount,
                             Model model) {
        model.addAttribute("tabCount", tabCount);
        return "startVideo";
    }

    private String mapToJsonString(Map<?, ?> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("Unable to parse map to json");
            return "";
        }
    }

}
