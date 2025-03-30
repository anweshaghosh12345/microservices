package com.microservice.user.service.UserService.services.impl;

import com.microservice.user.service.UserService.entities.User;
import com.microservice.user.service.UserService.exceptions.ResourceNotFoundException;
import com.microservice.user.service.UserService.repository.UserRepository;
import com.microservice.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        String randomId= UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with " +
                "given id is not found on Server !! :"+id));
    }

    @Override
    public Void removeUser(String user) {

        userRepository.deleteById(user);
        return null;
    }
}
