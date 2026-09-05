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
    @Value("${spring.kafka.topics.partitions}")
    private int partitions;
    @Value("${spring.kafka.topics.replicas}")
    private int replicas;
    @Value("${spring.kafka.topics.min.insync.replicas}")
    private String minIsr;

    @Bean
    public NewTopic createInvalidateSubscriptionCacheTopic() {
        return TopicBuilder.name(cancelEvent)
                .partitions(partitions)
                .replicas(replicas)
                .configs(Map.of("min.insync.replicas", minIsr))
                .build();
    }
}
