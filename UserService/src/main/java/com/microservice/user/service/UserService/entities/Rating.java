package com.microservice.user.service.UserService.entities;

import jdk.jfr.Name;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
