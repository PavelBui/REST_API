package com.epam.learning.backendservices.rest.converter;

import com.epam.learning.backendservices.rest.dto.SubscriptionRequestDto;
import com.epam.learning.backendservices.rest.model.Subscription;
import com.epam.learning.backendservices.rest.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionRequestDtoToSubscriptionConvertor {

    @Autowired
    private UserService userService;

    public Subscription convert(SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = new Subscription();
        BeanUtils.copyProperties(subscriptionRequestDto, subscription);
        subscription.setUser(userService.getUser(subscriptionRequestDto.getUserId()));
        return subscription;
    }
}
