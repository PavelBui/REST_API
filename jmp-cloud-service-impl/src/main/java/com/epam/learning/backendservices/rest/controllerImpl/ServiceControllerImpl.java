package com.epam.learning.backendservices.rest.controllerImpl;

import com.epam.learning.backendservices.rest.controller.ServiceController;
import com.epam.learning.backendservices.rest.dto.SubscriptionRequestDto;
import com.epam.learning.backendservices.rest.dto.SubscriptionResponseDto;
import com.epam.learning.backendservices.rest.serviceImpl.SubscriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceControllerImpl implements ServiceController {

    @Autowired
    private SubscriptionServiceImpl subscriptionServiceImpl;

    public EntityModel<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {

    }

    public EntityModel<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {

    }

    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {

    }

    public EntityModel<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {

    }

    public List<EntityModel<SubscriptionResponseDto>> getAllSubscription() {

    }
}
