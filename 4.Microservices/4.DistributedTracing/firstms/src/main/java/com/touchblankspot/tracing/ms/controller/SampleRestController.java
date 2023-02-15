package com.touchblankspot.tracing.ms.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SampleRestController {
	
	@NonNull
	private final RestTemplate restTemplate;

	@Value("${ms.base.url.second}")
	private String baseUrl;
	
	@GetMapping(value = "/first-ms")
	public String firstMicroservicesRequest() {
		log.info("Inside firstMicroservicesRequest");
		String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		log.info("The response received by method1 is " + response);
		return response;
	}
}
