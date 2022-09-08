package com.touchblankspot.ms.samplems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SampleMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleMsApplication.class, args);
    }

}
