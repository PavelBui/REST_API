package com.epam.learning.backendservices.rest.service;

import com.epam.learning.backendservices.rest.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    Subscription createSubscription(Subscription subscription);

    Subscription updateSubscription(Subscription subscription);

    void deleteSubscription(Long id);

    Optional<Subscription> getSubscription(Long id);

    List<Subscription> getAllSubscription();
}
