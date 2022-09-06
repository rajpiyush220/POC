package com.touchblankspot.message.kafka.producer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

    @Value("${kafka.topicName}")
    private String topicName;

    @NonNull
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedRate = 60000, initialDelay = 5000)
    public void producePeriodicMessage() {
        String message = String.format("Sample Message generated at %s.", LocalDateTime.now());
        kafkaTemplate.send(topicName, message);
        log.info("Produced : {}", message);
    }

}
