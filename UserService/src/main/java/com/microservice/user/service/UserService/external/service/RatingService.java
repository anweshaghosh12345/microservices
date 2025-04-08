package com.microservice.user.service.UserService.external.service;

import com.microservice.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
@FeignClient(name ="Rating-Service")
public interface RatingService {

    @GetMapping("ratings/users/ratingByUserId/{userId}")
    List<Rating> getRating(@PathVariable("userId") Long userId);

//    post
    @PostMapping("/ratings/save")
    public  Rating createRating(Rating values);

//    update
    @PutMapping("ratings/update/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") Long ratingId,Rating rating);

//    Delete
    @DeleteMapping("ratings/delete/id")
    public Void deleteRating(@PathVariable("id") Long id);

}
