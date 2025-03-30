package com.microservice.user.service.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		System.out.println("App Started");
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
