package com.microservice.user.service.UserService.controller;

import com.microservice.user.service.UserService.entities.Rating;
import com.microservice.user.service.UserService.entities.User;
import com.microservice.user.service.UserService.services.UserService;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

//    Create
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1= userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount=1;
//    Single User get
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable Long userId){
       User user= userService.getUser(userId);
        System.out.println("retryCount: "+retryCount);
        retryCount++;
       return ResponseEntity.ok(user);
    }

//    creating method for fall back circuitbreaker


    public ResponseEntity<User>ratingHotelFallback(Long userId,Exception ex){
        System.out.println("Fallback is executed as service is down : "+ex.getMessage());
//        log.info("retryCount : ",retryCount);
//        System.out.println(ex.getMessage());
        Long i=123l;
        User user=User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("dummy user")
                .userId(++i)
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


//    All user get
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        System.out.println("Upto here");
            List<User> user=userService.getAllUser();
            if(user==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        return ResponseEntity.ok(user);
    }
//    Delete user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        User user= userService.getUser(userId);
        if(user!=null)
        {
            userService.removeUser(userId);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

    }

//    create Rating
    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 =userService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
}
