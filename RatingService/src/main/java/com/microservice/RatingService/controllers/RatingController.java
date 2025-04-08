package com.microservice.RatingService.controllers;

import com.microservice.RatingService.entities.Rating;
import com.microservice.RatingService.services.RatingService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

//    create Rating
    @PostMapping("/save")
    public ResponseEntity<Rating> save(@RequestBody Rating rating){
        Rating rating1=ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
// update
    @PutMapping("/update/{ratingId}")
    public ResponseEntity<Rating>update(@PathVariable Long ratingId
            ,@RequestBody Rating rating){
        Rating rating1=ratingService.update(ratingId,rating);
        return ResponseEntity.status(HttpStatus.OK).body(rating1);
    }

//  get all ratings
    @GetMapping("/GetAllRatings")
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> ratings=ratingService.getRatings();
        return ResponseEntity.status(HttpStatus.OK).body(ratings);
}

//    find by userId
    @GetMapping("/users/ratingByUserId/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable Long userId){
        List<Rating> ratings=ratingService.getRatingsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ratings);
    }

//    get ratings by hotel id
    @GetMapping("/ratingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable Long hotelId){
        List<Rating> ratings=ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(ratings);
    }

//    Delete hotels by id
    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long ratingId){
        String delete=ratingService.delete(ratingId);
        return ResponseEntity.status(HttpStatus.OK).body(delete);
    }
}
