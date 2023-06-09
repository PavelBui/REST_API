package com.epam.learning.backendservices.rest.service.impl;

import com.epam.learning.backendservices.rest.exeption.UserNotFoundException;
import com.epam.learning.backendservices.rest.model.Subscription;
import com.epam.learning.backendservices.rest.repository.SubscriptionRepository;
import com.epam.learning.backendservices.rest.service.UserService;
import com.epam.learning.backendservices.rest.repository.UserRepository;
import com.epam.learning.backendservices.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        List<Subscription> subscriptions = (List<Subscription>) subscriptionRepository.findAll();
        subscriptions.forEach(subscription -> {
                    if (Objects.equals(subscription.getUser().getId(), id)) {
                        subscriptionRepository.deleteById(subscription.getId());
                    }
                });
        userRepository.deleteById(user.getId());
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }
}
