package com.microservice.SpringSecurity.controller;

import com.microservice.SpringSecurity.dto.AuthRequest;
import com.microservice.SpringSecurity.entity.UserInfo;
import com.microservice.SpringSecurity.service.AuthService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserInfo> addNewUser(@RequestBody UserInfo userInfo){
        UserInfo userInfo1=authService.saveUser(userInfo);
        return new ResponseEntity<>(userInfo1, HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        return authService.generateToken(authRequest.getUsername());
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "token is valid";
    }
}
