package com.microservice.user.service.UserService.controller;

import com.microservice.user.service.UserService.entities.Rating;
import com.microservice.user.service.UserService.entities.User;
import com.microservice.user.service.UserService.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    Create
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
       User user1= userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
//    Single User get
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long userId){
       User user= userService.getUser(userId);
       return ResponseEntity.ok(user);
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
    @PostMapping("/delete/{userId}")
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
