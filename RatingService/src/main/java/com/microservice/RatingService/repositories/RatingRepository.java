package com.microservice.RatingService.repositories;

import com.microservice.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RatingRepository extends JpaRepository<Rating,Long> {

//    custom finder methods
    List<Rating> findByUserId(Long userId);
    List<Rating> findByHotelId(Long hotelId);
}
