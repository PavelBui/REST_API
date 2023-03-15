package com.epam.learning.backendservices.rest.service.impl;

import com.epam.learning.backendservices.rest.service.UserService;
import com.epam.learning.backendservices.rest.repository.UserRepository;
import com.epam.learning.backendservices.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Long id = user.getId();
        return userRepository.findById(id)
                .orElseGet(() -> {
                    user.setId(null);
                    return userRepository.save(user);
                });
    }

    @Override
    public User updateUser(User user) {
        Long id = user.getId() != null ? user.getId() : 0L;
        return userRepository.findById(id)
                .map(currentUser -> {
                    currentUser.setName(user.getName());
                    currentUser.setSurname(user.getSurname());
                    currentUser.setBirthday(user.getBirthday());
                    return userRepository.save(currentUser);
                })
                .orElseGet(() -> userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }
}
