package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topics.cancel-event}")
    private String cancelEvent;

    @Bean
    public NewTopic createInvalidateSubscriptionCacheTopic() {
        return TopicBuilder.name(cancelEvent)
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
