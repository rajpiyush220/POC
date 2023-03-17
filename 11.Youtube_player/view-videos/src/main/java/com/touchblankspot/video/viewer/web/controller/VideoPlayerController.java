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

    @GetMapping(value = {"/play", "/watchhour"})
    public String playSpecificVideo(@RequestParam(value = "videoId", defaultValue = "") String videoId,
                                    @RequestParam(value = "recentLimit", defaultValue = "0") Integer recentLimit,
                                    @RequestParam(value = "pageSize", defaultValue = "0") Integer pageSize,
                                    @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                    Model model) {
        Map<String, String> videoDetails = videoService.getWatchVideoDetails(videoId, recentLimit, pageSize, pageNo);
        model.addAttribute("jsonData", mapToJsonString(videoDetails));
        model.addAttribute("maxDuration", "0");
        model.addAttribute("title", "Increase Watch Hour");
        return "playVideo";
    }

    @GetMapping(value = {"/count", "/viewcount"})
    public String increaseViewCountSpecificVideo(@RequestParam(value = "videoId", defaultValue = "") String videoId,
                                                 @RequestParam(value = "recentLimit", defaultValue = "0") Integer recentLimit,
                                                 Model model) {
        Map<String, String> videoDetails = videoService.getWatchVideoDetails(videoId, recentLimit);
        model.addAttribute("jsonData", mapToJsonString(videoDetails));
        int maxDurationForViewCount = 45000;
        model.addAttribute("maxDuration", maxDurationForViewCount);
        model.addAttribute("title", "Increase Watch Hour");
        return "playVideo";
    }

    @GetMapping(value = {"/start"})
    public String playVideos(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             Model model) {
        int totalVideo = videoService.getVideoCount();
        int pageCount = totalVideo;
        if (totalVideo > pageSize) {
            pageCount = totalVideo / pageSize;
            pageCount += (totalVideo % pageSize > 0 ? 1 : 0);
        }
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageCount", pageCount);
        return "startVideo";
    }

    @GetMapping(value = {"/playBySize"})
    public String playLargeVideo(@RequestParam(value = "playSmall", defaultValue = "false") Boolean playSmall, Model model) {
        long largeVideoMinLength = 100 * 1000;
        Map<String, String> videoDetails = videoService.getVideoDetailsByLength(largeVideoMinLength, playSmall);
        model.addAttribute("jsonData", mapToJsonString(videoDetails));
        model.addAttribute("maxDuration", "0");
        model.addAttribute("title", String.format("Playing %s video", playSmall ? "Small" : "Large"));
        return "playVideo";
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
