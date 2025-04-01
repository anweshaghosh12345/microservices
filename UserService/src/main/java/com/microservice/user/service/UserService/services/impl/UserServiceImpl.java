package com.microservice.user.service.UserService.services.impl;

import com.microservice.user.service.UserService.entities.Rating;
import com.microservice.user.service.UserService.entities.User;
import com.microservice.user.service.UserService.exceptions.ResourceNotFoundException;
import com.microservice.user.service.UserService.repository.UserRepository;
import com.microservice.user.service.UserService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

//    private Logger logg= LoggerFactory.getLogger(UserServiceImpl.class);

//    public UserServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public User saveUser(User user) {
//        String randomId= UUID.randomUUID().toString();
//        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> alluser=userRepository.findAll();
        List<User> userSet=new ArrayList<>();
        for(int i=0;i<alluser.size();i++){
            User user=alluser.get(i);
            //        fetch ratings of the above user from rating service
            ArrayList<Rating> ratingsofUsers=restTemplate.getForObject
                    ("http://localhost:8083/ratings/users/ratingByUserId/"+user.getUserId(),
                            ArrayList.class);
            user.setRating(ratingsofUsers);
        userSet.add(user);
        }

        return userSet;
    }

    @Override
    public User getUser(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with " +
                "given id is not found on Server !! :"+id));
//        fetch ratings of the above user from rating service
//    http://localhost:8083/ratings/users/ratingByUserId/5
        ArrayList<Rating> ratingsofUsers=restTemplate.getForObject
                ("http://localhost:8083/ratings/users/ratingByUserId/"+user.getUserId(),
                ArrayList.class);
        System.out.println("ratings of users: "+ratingsofUsers);

        user.setRating(ratingsofUsers);
        return user;
    }

    @Override
    public Void removeUser(Long user) {

        userRepository.deleteById(user);
        return null;
    }
}
