package com.microservice.RatingService.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="micro_ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ratingId;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "hotelId")
    private Long hotelId;
    @Column(name = "ratings")
    private int rating;
    @Column(name = "feedback",length = 100)
    private String feedback;
}
