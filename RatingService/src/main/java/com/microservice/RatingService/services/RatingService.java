package com.microservice.RatingService.services;

import com.microservice.RatingService.entities.Rating;
import org.springframework.stereotype.Service;
import java.util.*;

public interface RatingService {
//    Create
    Rating create(Rating rating);
//    get all rating
    List<Rating> getRatings();
//    get all by userid
    List<Rating> getRatingsByUserId(Long userId);
//    get all by hotel
    List<Rating> getRatingsByHotelId(Long hotelId);
//    delete Hotel by id
    String delete(Long id);
}
