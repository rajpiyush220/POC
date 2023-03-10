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

@Controller
@RequestMapping("/video/player")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class VideoPlayerController {

    @NonNull
    private final VideoService videoService;

    @GetMapping("/viewcount")
    public String increaseViewCount(Model model){
        try{
            model.addAttribute("jsonData",new ObjectMapper().writeValueAsString(videoService.getWatchVideoDetails()));
        } catch (JsonProcessingException e) {
            model.addAttribute("jsonData","");
        }
        return "increaseViewCount";
    }

    @GetMapping("/watchhour")
    public String increaseWatchHour(Model model){
        try{
            model.addAttribute("jsonData",new ObjectMapper().writeValueAsString(videoService.getWatchVideoDetails()));
        } catch (JsonProcessingException e) {
            model.addAttribute("jsonData","");
        }
        return "increaseWatchHour";
    }

}
