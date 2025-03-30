package com.microservice.hotel.controllers;

import com.microservice.hotel.entities.Hotel;
import com.microservice.hotel.service.HotelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
//    save
    @PostMapping("/save")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1=hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }


//    get all
    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> getAll(){
        List<Hotel> hotelList=hotelService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(hotelList);

    }

//    get one

    @GetMapping("/getOne/{hotelId}")
    public ResponseEntity<Hotel> getOne(@PathVariable String hotelId){
        Hotel hotel1=hotelService.getSingle(hotelId);
//        if(hotel1!=null)
        return ResponseEntity.status(HttpStatus.OK).body(hotel1);
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

//    delete

    @PostMapping("/delete/{hotelId}")
    public ResponseEntity<String> deletehotel(@PathVariable String hotelId){
        if(hotelService.getSingle(hotelId)!=null)
        {
            hotelService.delete(hotelId);
            return ResponseEntity.status(HttpStatus.OK).body("Hotel is deleted");
        }
        else
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Hotel Found");

    }
}
