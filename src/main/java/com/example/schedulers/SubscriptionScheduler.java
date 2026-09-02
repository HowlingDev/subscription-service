package com.example.schedulers;

import com.example.dto.SubscriptionKafkaDto;
import com.example.entities.SubscriptionEntity;
import com.example.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SubscriptionScheduler {

    private final KafkaTemplate<String, SubscriptionKafkaDto> kafkaTemplate;
    private final SubscriptionService subscriptionService;
    @Value("${spring.kafka.topics.cancel-event}")
    private String cancelEvent;

    @Scheduled(fixedRate = 300000)
    @SchedulerLock(
            name = "SubscriptionScheduler_cancelPaidSubscription",
            lockAtMostFor = "PT1M"
    )
    public void cancelPaidSubscription() {
        List<SubscriptionEntity> entities = subscriptionService.getSubscriptionListToCancelPaidSubscription(
                OffsetDateTime.now()
        );
        if (entities.isEmpty()) {
            return;
        }
        entities.stream().forEach(entity -> {
            entity.setSubscriptionType(SubscriptionEntity.SubscriptionType.FREE);
            subscriptionService.saveSubscription(entity);
            kafkaTemplate.send(cancelEvent, new SubscriptionKafkaDto(entity.getLogin()));
        });
    }
}
