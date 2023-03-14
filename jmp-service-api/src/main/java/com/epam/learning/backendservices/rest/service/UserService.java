package com.epam.learning.backendservices.rest.service;

import com.epam.learning.backendservices.rest.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUser(Long id);

    List<User> getAllUser();

}
