package com.epam.learning.backendservices.rest.dao;

import com.epam.learning.backendservices.rest.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
