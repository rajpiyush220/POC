package com.touchblankspot.tracing.ms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class SampleRestController {

    @GetMapping(value = "/fourth-ms")
    public String fourthMicroservicesRequest() {
        log.info("Inside fourthMicroservicesRequest");
        log.info("Sending response from Fourth Microservices. {}", LocalDateTime.now());
        return "Sending response from Fourth Microservices at "+LocalDateTime.now();
    }
}
