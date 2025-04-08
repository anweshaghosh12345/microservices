package com.microservice.RatingService.services.impl;

import com.microservice.RatingService.entities.Rating;
import com.microservice.RatingService.repositories.RatingRepository;
import com.microservice.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating create(Rating rating) {
//        String id=UUID.randomUUID();
//        rating.setRatingId(id);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(Long hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public String delete(Long id) {
        Rating rating=ratingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ratings not found"));
        ratingRepository.deleteById(id);
        return "Ratings deleted";
    }

    @Override
    public Rating update(Long ratingId, Rating rating) {

        Rating rating1=ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ratings not found"));
        rating1.setRating(rating.getRating());
        rating1.setHotelId(rating.getHotelId());
        rating1.setUserId(rating.getUserId());
        rating1.setFeedback(rating.getFeedback());
        Rating rating2=ratingRepository.save(rating1);
        return rating2;
    }
}
