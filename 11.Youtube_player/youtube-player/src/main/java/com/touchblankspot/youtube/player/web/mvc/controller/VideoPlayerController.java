package com.touchblankspot.youtube.player.web.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.touchblankspot.youtube.player.service.YoutubeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/video/player")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class VideoPlayerController {

    @NonNull
    private final YoutubeService videoService;

    @NonNull
    private final ObjectMapper objectMapper;

    @GetMapping(value = {"/play", "/watchhour"})
    public String playSpecificVideo(Model model) {
        Map<String, String> videoDetails = videoService.getVideoDetails();
        model.addAttribute("jsonData", mapToJsonString(videoDetails));
        model.addAttribute("title", "Increase Watch Hour");
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
