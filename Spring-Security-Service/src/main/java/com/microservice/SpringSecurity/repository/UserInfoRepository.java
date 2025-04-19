package com.microservice.SpringSecurity.repository;

import com.microservice.SpringSecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
}
