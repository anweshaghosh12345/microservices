package com.microservice.hotel.service;

import com.microservice.hotel.entities.Hotel;
import java.util.*;

public interface HotelService {
//    create
    Hotel create(Hotel hotel);
//    get all
    List<Hotel> getAll();
//    get single
    Hotel getSingle(Long id);

//    delete
    String delete(Long id);
}
