package com.epam.learning.backendservices.rest.service.impl;

import com.epam.learning.backendservices.rest.exeption.SubscriptionNotFoundException;
import com.epam.learning.backendservices.rest.service.SubscriptionService;
import com.epam.learning.backendservices.rest.repository.SubscriptionRepository;
import com.epam.learning.backendservices.rest.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        Long id = subscription.getId();
        return subscriptionRepository.findById(id)
                .orElseGet(() -> {
                    subscription.setId(null);
                    subscription.setStartDate(LocalDate.now());
                    return subscriptionRepository.save(subscription);
                });
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        Long id = subscription.getId() != null ? subscription.getId() : 0L;
        return subscriptionRepository.findById(id)
                .map(currentSubscription -> {
                    currentSubscription.setUser(subscription.getUser());
                    currentSubscription.setStartDate(LocalDate.now());
                    return subscriptionRepository.save(currentSubscription);
                })
                .orElseGet(() -> subscriptionRepository.save(subscription));
    }

    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
        subscriptionRepository.deleteById(subscription.getId());
    }

    @Override
    public Optional<Subscription> getSubscription(Long id) {
        return subscriptionRepository.findById(id);
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }
}
