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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
@RequestMapping("/play")
public class PlayerController {

	@NonNull
	private final YoutubeService youtubeService;

	@NonNull
	private final ObjectMapper objectMapper;

	@GetMapping("/")
	public String playAll(Model model) {
		Map<String, String> videoDetails = youtubeService.getVideoDetails();
		model.addAttribute("jsonData", mapToJsonString(videoDetails));
		model.addAttribute("title", "Playing Mixed Video");
		model.addAttribute("contentText", "Mixed");
		return "playVideo";
	}

	@GetMapping("/shorts")
	public String playShots(@RequestParam(value = "videoId", defaultValue = "") String videoId,
			@RequestParam(value = "playSequential", defaultValue = "0") Integer playSequential, Model model) {
		return playVideoOrShot(true, model, videoId, playSequential);
	}

	@GetMapping("/videos")
	public String playVideos(@RequestParam(value = "videoId", defaultValue = "") String videoId,
			@RequestParam(value = "playSequential", defaultValue = "0") Integer playSequential, Model model) {
		return playVideoOrShot(false, model, videoId, playSequential);
	}

	private String playVideoOrShot(Boolean isShot, Model model, String videoId, Integer playSequential) {
		Map<String, String> videoDetails = youtubeService.getVideoPlayDetails(isShot, videoId);
		model.addAttribute("jsonData", mapToJsonString(videoDetails));
		model.addAttribute("title", isShot ? "Playing Shorts" : "Playing Video");
		model.addAttribute("contentText", isShot ? "Shorts" : "Video");
		model.addAttribute("playSequential", playSequential);
		return "playVideo";
	}

	private String mapToJsonString(Map<?, ?> map) {
		try {
			return objectMapper.writeValueAsString(map);
		}
		catch (JsonProcessingException e) {
			log.error("Unable to parse map to json");
			return "";
		}
	}

}
