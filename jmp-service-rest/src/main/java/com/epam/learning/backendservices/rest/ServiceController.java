package com.epam.learning.backendservices.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subscription")
public interface ServiceController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto);

    @PutMapping
    EntityModel<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<String> deleteSubscription(@PathVariable Long id);

    @GetMapping("/{id}")
    EntityModel<SubscriptionResponseDto> getSubscription(@PathVariable Long id);

    @GetMapping
    List<EntityModel<SubscriptionResponseDto>> getAllSubscription();
}
