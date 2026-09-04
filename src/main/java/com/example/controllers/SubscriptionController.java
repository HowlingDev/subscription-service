package com.example.controllers;

import com.example.entities.SubscriptionEntity;
import com.example.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/check/{login}")
    public String checkSubscription(@PathVariable String login) {
        SubscriptionEntity entity = subscriptionService.getSubscription(login);
        return subscriptionService.checkSubscription(entity).toString();
    }
}
