package com.touchblankspot.youtube.player;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
@EnableBatchProcessing
@EnableScheduling
public class YoutubePlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubePlayerApplication.class, args);
	}

}
