package com.epam.learning.backendservices.rest.controller.impl;

import com.epam.learning.backendservices.rest.controller.ServiceController;
import com.epam.learning.backendservices.rest.converter.SubscriptionRequestDtoToSubscriptionConvertor;
import com.epam.learning.backendservices.rest.converter.SubscriptionToSubscriptionResponseDtoConvertor;
import com.epam.learning.backendservices.rest.dto.SubscriptionRequestDto;
import com.epam.learning.backendservices.rest.dto.SubscriptionResponseDto;
import com.epam.learning.backendservices.rest.exeption.SubscriptionNotFoundException;
import com.epam.learning.backendservices.rest.model.Subscription;
import com.epam.learning.backendservices.rest.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Api(tags = "Subscription Endpoint")
public class ServiceControllerImpl implements ServiceController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private SubscriptionToSubscriptionResponseDtoConvertor subscriptionToSubscriptionResponseDtoConvertor;
    @Autowired
    private SubscriptionRequestDtoToSubscriptionConvertor subscriptionRequestDtoToSubscriptionConvertor;

    @ApiOperation("Create Subscription")
    public EntityModel<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionService.createSubscription(
                subscriptionRequestDtoToSubscriptionConvertor.convert(subscriptionRequestDto));
        return toModel(subscriptionToSubscriptionResponseDtoConvertor.convert(subscription));
    }

    @ApiOperation("Update Subscription")
    public EntityModel<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionService.updateSubscription(
                subscriptionRequestDtoToSubscriptionConvertor.convert(subscriptionRequestDto));
        return toModel(subscriptionToSubscriptionResponseDtoConvertor.convert(subscription));
    }

    @ApiOperation("Delete Subscription by id")
    @ApiImplicitParam(name = "id", value = "Subscription id", paramType = "path", required = true)
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Get Subscription by id")
    @ApiImplicitParam(name = "id", value = "Subscription id", paramType = "path", required = true)
    public EntityModel<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {
        return toModel(subscriptionToSubscriptionResponseDtoConvertor.convert(
                subscriptionService.getSubscription(id)
                        .orElseThrow(() -> new SubscriptionNotFoundException((id)))));
    }

    @ApiOperation("Get list of all Subscriptions")
    public List<EntityModel<SubscriptionResponseDto>> getAllSubscription() {
        return subscriptionService.getAllSubscription()
                .stream()
                .map(subscription -> subscriptionToSubscriptionResponseDtoConvertor.convert(subscription))
                .map(ServiceControllerImpl::toModel)
                .collect(Collectors.toList());
    }

    private static EntityModel<SubscriptionResponseDto> toModel(SubscriptionResponseDto response) {
        return EntityModel.of(response,
                linkTo(methodOn(ServiceControllerImpl.class).getSubscription(response.getId())).withSelfRel(),
                linkTo(methodOn(ServiceControllerImpl.class).getAllSubscription()).withRel("getAllSubscriptions"),
                linkTo(methodOn(ServiceControllerImpl.class).deleteSubscription(response.getId())).withRel("deleteSubscription"),
                linkTo(methodOn(ServiceControllerImpl.class).createSubscription(new SubscriptionRequestDto())).withRel("createSubscription"),
                linkTo(methodOn(ServiceControllerImpl.class).updateSubscription(new SubscriptionRequestDto())).withRel("updateSubscription")
        );
    }
}
