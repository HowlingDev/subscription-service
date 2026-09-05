package com.example.services;

import com.example.dto.SubscriptionTypeDto;
import com.example.entities.SubscriptionEntity;
import com.example.exceptions.SubscriptionNotFoundException;
import com.example.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Transactional(readOnly = true)
    public List<SubscriptionEntity> getSubscriptionListToCancelPaidSubscription(OffsetDateTime dateTime) {
        return subscriptionRepository.findByExpirationDateBeforeAndSubscriptionType(
                dateTime, SubscriptionEntity.SubscriptionType.PAID
        );
    }

    @Transactional
    public void saveSubscription(SubscriptionEntity entity) {
        subscriptionRepository.save(entity);
    }

    public SubscriptionEntity getSubscription(String login) {
        return subscriptionRepository.findByLogin(login)
                .orElseThrow(() -> new SubscriptionNotFoundException("Не удалось найти подписку с логином %s".formatted(login)));
    }

    public SubscriptionTypeDto checkSubscription(String login) {
        SubscriptionEntity entity = getSubscription(login);
        return !(entity.getSubscriptionType() == SubscriptionEntity.SubscriptionType.PAID &&
                entity.getExpirationDate().isAfter(OffsetDateTime.now())) ?
                new SubscriptionTypeDto(SubscriptionEntity.SubscriptionType.FREE) :
                new SubscriptionTypeDto(SubscriptionEntity.SubscriptionType.PAID);
    }
}
