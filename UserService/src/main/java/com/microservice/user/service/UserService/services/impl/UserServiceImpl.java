package com.microservice.user.service.UserService.services.impl;

import com.microservice.user.service.UserService.entities.Hotel;
import com.microservice.user.service.UserService.entities.Rating;
import com.microservice.user.service.UserService.entities.User;
import com.microservice.user.service.UserService.exceptions.ResourceNotFoundException;
import com.microservice.user.service.UserService.repository.UserRepository;
import com.microservice.user.service.UserService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
//            ArrayList<Rating> ratingsofUsers=restTemplate.getForObject
//                    ("http://localhost:8083/ratings/users/ratingByUserId/"+user.getUserId(),
//                            ArrayList.class);
            Rating[] ratingsofUsers=restTemplate.getForObject
                    ("http://RATING-SERVICE/ratings/users/ratingByUserId/"+user.getUserId(),
                            Rating[].class);
            List<Rating> ratings=Arrays.stream(ratingsofUsers).toList();
            List<Rating> ratingList = new ArrayList<>();
            for (Rating rating : ratings) {
                Hotel hotel = restTemplate.getForObject(
                        "http://HOTEL-SERVICE/hotels/getOne/" + rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
                ratingList.add(rating);
            }
            user.setRating(ratingList);
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
        Rating[] ratingsofUsers=restTemplate.getForObject
                ("http://RATING-SERVICE/ratings/users/ratingByUserId/"+user.getUserId(),
                Rating[].class);
        List<Rating> ratings=Arrays.stream(ratingsofUsers).toList();
        List<Rating> ratingList = new ArrayList<>();
        for (Rating rating : ratings) {
            Hotel hotel = restTemplate.getForObject(
                    "http://HOTEL-SERVICE/hotels/getOne/" + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
            ratingList.add(rating);
        }
//       List<Rating> ratingList= ratings.stream().map(rating ->{
////           api call to hotel service to get the hotel
////          http://localhost:8082/hotels/getOne/1
//            ResponseEntity<Hotel> forEntity= restTemplate.getForEntity
//                    ("http://localhost:8082/hotels/getOne/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel=forEntity.getBody();
//           System.out.println("hotel ratings"+ forEntity.getStatusCode());
//           rating.setHotel(hotel);
////           set the hotel to rating
////           return rating
//            return rating;
//        } ).collect(Collectors.toList());
        System.out.println("ratings of users: "+ratingsofUsers);

        user.setRating(ratingList);
        return user;
    }

    @Override
    public Void removeUser(Long user) {

        userRepository.deleteById(user);
        return null;
    }
}
