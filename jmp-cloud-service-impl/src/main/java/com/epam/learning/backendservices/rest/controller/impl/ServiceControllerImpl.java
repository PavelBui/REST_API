package com.epam.learning.backendservices.rest.controller.impl;

import com.epam.learning.backendservices.rest.controller.ServiceController;
import com.epam.learning.backendservices.rest.converter.SubscriptionRequestDtoToSubscriptionConvertor;
import com.epam.learning.backendservices.rest.converter.SubscriptionToSubscriptionResponseDtoConvertor;
import com.epam.learning.backendservices.rest.dto.SubscriptionRequestDto;
import com.epam.learning.backendservices.rest.dto.SubscriptionResponseDto;
import com.epam.learning.backendservices.rest.model.Subscription;
import com.epam.learning.backendservices.rest.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ServiceControllerImpl implements ServiceController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private SubscriptionToSubscriptionResponseDtoConvertor subscriptionToSubscriptionResponseDto;
    @Autowired
    private SubscriptionRequestDtoToSubscriptionConvertor subscriptionRequestDtoToSubscription;

    public EntityModel<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionService.createSubscription(
                subscriptionRequestDtoToSubscription.convert(subscriptionRequestDto));
        return toModel(subscriptionToSubscriptionResponseDto.convert(subscription));
    }

    public EntityModel<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionService.updateSubscription(
                subscriptionRequestDtoToSubscription.convert(subscriptionRequestDto));
        return toModel(subscriptionToSubscriptionResponseDto.convert(subscription));
    }

    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok().build();
    }

    public EntityModel<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {
        return toModel(subscriptionToSubscriptionResponseDto.convert(
                subscriptionService.getSubscription(id)));
    }

    public List<EntityModel<SubscriptionResponseDto>> getAllSubscription() {
        return subscriptionService.getAllSubscription()
                .stream()
                .map(subscription -> subscriptionToSubscriptionResponseDto.convert(subscription))
                .map(ServiceControllerImpl::toModel)
                .collect(Collectors.toList());
    }

    private static EntityModel<SubscriptionResponseDto> toModel(SubscriptionResponseDto response) {
        return EntityModel.of(response,
                linkTo(methodOn(ServiceController.class).getSubscription(response.getId())).withSelfRel(),
                linkTo(methodOn(ServiceController.class).getAllSubscription()).withRel("getAllSubscriptions"),
                linkTo(methodOn(ServiceController.class).deleteSubscription(response.getId())).withRel("deleteSubscription"),
                linkTo(methodOn(ServiceController.class).createSubscription(new SubscriptionRequestDto())).withRel("createSubscription"),
                linkTo(methodOn(ServiceController.class).updateSubscription(new SubscriptionRequestDto())).withRel("updateSubscription")
        );
    }
}
