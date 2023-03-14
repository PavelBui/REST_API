package com.epam.learning.backendservices.rest.Service;

import com.epam.learning.backendservices.rest.SubscriptionService;
import com.epam.learning.backendservices.rest.dao.SubscriptionRepository;
import com.epam.learning.backendservices.rest.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        subscription.setId(null);
        subscription.setStartDate(LocalDate.now());
        return subscriptionRepository.save(subscription);
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
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id).get();
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }
}
