package com.example.repositories;

import com.example.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {

    List<SubscriptionEntity> findByExpirationDateBeforeAndSubscriptionType(
            OffsetDateTime dateTime, SubscriptionEntity.SubscriptionType type
    );

    Optional<SubscriptionEntity> findByLogin(String login);
}
