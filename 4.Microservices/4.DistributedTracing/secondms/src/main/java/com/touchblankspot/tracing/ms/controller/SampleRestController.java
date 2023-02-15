package com.touchblankspot.tracing.ms.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SampleRestController {
	
	@NonNull
	private final RestTemplate restTemplate;

	@Value("${ms.base.url.third}")
	private String baseUrl;
	
	@GetMapping(value = "/second-ms")
	public String secondMicroservicesRequest() {
		log.info("Inside secondMicroservicesRequest");
		String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
		log.info("The response received by Third Ms is " + response);
		return response;
	}
}
