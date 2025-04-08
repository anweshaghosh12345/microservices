package com.microservice.user.service.UserService.external.service;

import com.microservice.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Hotel-Service")
public interface HotelService {

    @GetMapping("/hotels/getOne/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") Long hotelId);
}
