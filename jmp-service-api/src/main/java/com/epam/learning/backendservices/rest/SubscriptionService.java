package com.epam.learning.backendservices.rest;

import com.epam.learning.backendservices.rest.model.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription createSubscription(Subscription subscription);

    Subscription updateSubscription(Subscription subscription);

    void deleteSubscription(Long id);

    Subscription getSubscription(Long id);

    List<Subscription> getAllSubscription();
}
