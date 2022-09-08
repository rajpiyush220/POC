package com.touchblankspot.ms.container.springbootdocker.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Welcome from Spring boot docker example HelloController";
    }
}
