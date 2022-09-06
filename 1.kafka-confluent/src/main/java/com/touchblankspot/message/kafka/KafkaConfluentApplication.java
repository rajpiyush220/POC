package com.touchblankspot.message.kafka;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class KafkaConfluentApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConfluentApplication.class, args);
	}

}
