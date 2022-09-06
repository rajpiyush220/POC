package com.touchblankspot.message.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "kafka")
@Data
@Component
public class KafkaConfigProperties {

    private String baseUrl;
    private DefaultConfig defaultConfig;
    private SecurityConfig securityConfig;

    @Data
    public static class Jaas{
        String config;
    }

    @Data
    public static class Sasl{
        String mechanism;
        Jaas jaas;
    }

    @Data
    public static class SecurityConfig{
        String protocol;
        String apiKey;
        String secretKey;
        Sasl sasl;
    }

    @Data
    public static class DefaultConfig{
        Integer partitionSize;
        Short replicationFactor;
    }
}
