package com.touchblankspot.message.kafka.config.initializer;

import com.touchblankspot.message.kafka.config.KafkaConfigProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TopicInitializer {

    @NonNull
    private final AdminClient kafkaAdminClient;

    @NonNull
    private final KafkaConfigProperties kafkaConfigProperties;

    @Value("${kafka.topicName}")
    private String topicName;

    @EventListener(ApplicationReadyEvent.class)
    public void createTopicIfNotExists() {
        try {
            Set<String> exsitingTopics = kafkaAdminClient.listTopics().names().get();
            if (!exsitingTopics.contains(topicName)) {
                kafkaAdminClient.createTopics(
                        Collections.singleton(new NewTopic(topicName, kafkaConfigProperties.getDefaultConfig().getPartitionSize(),
                                kafkaConfigProperties.getDefaultConfig().getReplicationFactor()))).all().get();
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("Kafka operation failed {}", e);
            throw new RuntimeException(e);
        }
    }
}
