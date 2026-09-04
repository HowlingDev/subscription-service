package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "subscriptions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionEntity {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(name = "expiration_date")
    private OffsetDateTime expirationDate;

    public enum SubscriptionType {
        PAID,
        FREE
    }

}
