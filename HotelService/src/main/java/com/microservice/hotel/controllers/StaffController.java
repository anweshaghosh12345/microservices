package com.microservice.hotel.controllers;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaffs(){
       List<String> arr= Arrays.asList("Ram","Sam","Sita","Gita");
        return new ResponseEntity<>(arr, HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }
}
