package com.touchblankspot.ms.central.config.web.controller;

import com.touchblankspot.ms.central.config.properties.CentralConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SampleController {

    @NonNull
    private final CentralConfig centralConfig;

    @GetMapping("/sayHello")
    public String sayHello(){
        return "sayHello from env "+centralConfig.getEnv();
    }
}
