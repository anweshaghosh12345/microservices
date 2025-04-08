package com.microservice.user.service.UserService.services.impl;

import com.microservice.user.service.UserService.entities.Hotel;
import com.microservice.user.service.UserService.entities.Rating;
import com.microservice.user.service.UserService.entities.User;
import com.microservice.user.service.UserService.exceptions.ResourceNotFoundException;
import com.microservice.user.service.UserService.external.service.HotelService;
import com.microservice.user.service.UserService.external.service.RatingService;
import com.microservice.user.service.UserService.repository.UserRepository;
import com.microservice.user.service.UserService.services.UserService;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Override
    public User saveUser(User user) {
//        String randomId= UUID.randomUUID().toString();
//        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public Rating createRating(Rating rating){
        Rating rating1=Rating.builder()
                .rating(rating.getRating())
                .userId(rating.getUserId())
                        .hotelId(rating.getHotelId())
                                .feedback(rating.getFeedback())
                                        .build();
        Rating rating2= ratingService.createRating(rating1);
        return rating2;
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
//            Rating[] ratingsofUsers=restTemplate.getForObject
//                    ("http://RATING-SERVICE/ratings/users/ratingByUserId/"+user.getUserId(),
//                            Rating[].class);
            List<Rating> ratings=ratingService.getRating(user.getUserId());
//            List<Rating> ratings=Arrays.stream(ratingsofUsers).toList();
            List<Rating> ratingList = new ArrayList<>();
            for (Rating rating : ratings) {
//                Hotel hotel = restTemplate.getForObject(
//                        "http://HOTEL-SERVICE/hotels/getOne/" + rating.getHotelId(), Hotel.class);
                Hotel hotel=hotelService.getHotel(rating.getHotelId());
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
//        Rating[] ratingsofUsers=restTemplate.getForObject
//                ("http://RATING-SERVICE/ratings/users/ratingByUserId/"+user.getUserId(),
//                Rating[].class);
        List<Rating> ratings=ratingService.getRating(user.getUserId());
//        List<Rating> ratings=Arrays.stream(ratingsofUsers).toList();
        List<Rating> ratingList = new ArrayList<>();
        for (Rating rating : ratings) {
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
//            Hotel hotel = restTemplate.getForObject(
//                    "http://HOTEL-SERVICE/hotels/getOne/" + rating.getHotelId(), Hotel.class);
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
//        System.out.println("ratings of users: "+ratingsofUsers);

        user.setRating(ratingList);
        return user;
    }

    @Override
    public Void removeUser(Long user) {

        userRepository.deleteById(user);
        return null;
    }
}
