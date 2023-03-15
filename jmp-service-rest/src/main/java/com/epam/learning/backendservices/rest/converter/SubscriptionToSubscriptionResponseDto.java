package com.epam.learning.backendservices.rest.converter;

import com.epam.learning.backendservices.rest.dto.SubscriptionResponseDto;
import com.epam.learning.backendservices.rest.model.Subscription;
import org.springframework.beans.BeanUtils;

public class SubscriptionToSubscriptionResponseDto {

    public SubscriptionResponseDto convert(Subscription subscription) {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        BeanUtils.copyProperties(subscription, subscriptionResponseDto);
        subscriptionResponseDto.setUserId(subscription.getUser().getId());
        subscriptionResponseDto.setStartDate(subscription.getStartDate().toString());
        return subscriptionResponseDto;
    }
}
