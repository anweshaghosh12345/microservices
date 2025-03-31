package com.microservice.user.service.UserService.services;

import com.microservice.user.service.UserService.entities.User;
import java.util.List;

public interface UserService {
//    Create user
    User saveUser(User user);

//    get all user
    List<User> getAllUser();

//    get single user
    User getUser(Long id);

//    delete user
    Void removeUser(Long userId);
//    Update user

}
