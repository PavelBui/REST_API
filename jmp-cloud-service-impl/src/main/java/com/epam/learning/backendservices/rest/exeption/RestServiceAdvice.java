package com.epam.learning.backendservices.rest.exeption;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestServiceAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    EntityModel<UserNotFoundException> userNotFoundHandler(UserNotFoundException ex) {
        return EntityModel.of(ex);
    }

    @ResponseBody
    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    EntityModel<SubscriptionNotFoundException> subscriptionNotFoundHandler(SubscriptionNotFoundException ex) {
        return EntityModel.of(ex);
    }
}
