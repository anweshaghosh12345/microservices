package com.microservice.SpringSecurity.service;

import com.microservice.SpringSecurity.entity.UserInfo;
import com.microservice.SpringSecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> userInfo=repository.findByName(username);


        return userInfo.map(CustomUserDetailsObject::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found :"+username));
    }
}
