package com.example.controllers;

import com.example.dto.SubscriptionTypeDto;
import com.example.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SubscriptionTypeDto> checkSubscription(@PathVariable String login) {
        return ResponseEntity.ok(subscriptionService.checkSubscription(login));
    }
}
