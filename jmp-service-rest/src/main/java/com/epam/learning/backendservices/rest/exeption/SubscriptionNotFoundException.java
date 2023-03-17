package com.epam.learning.backendservices.rest.exeption;

public class SubscriptionNotFoundException extends  RuntimeException{

    public SubscriptionNotFoundException(Long id) {
        super("Subscription with id: " + id + " not found");
    }
}
