package com.microservice.user.service.UserService.repository;

import com.microservice.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    void delete(Optional<User> byId);


}
