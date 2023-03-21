package com.epam.learning.backendservices.rest.exeption;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("User with id: " + id + " not found");
    }
}
