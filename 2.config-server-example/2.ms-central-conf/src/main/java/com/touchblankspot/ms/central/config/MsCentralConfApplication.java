package com.touchblankspot.ms.central.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class MsCentralConfApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCentralConfApplication.class, args);
	}

}
