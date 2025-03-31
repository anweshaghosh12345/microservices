package com.microservice.hotel.service.imple;

import com.microservice.hotel.entities.Hotel;
import com.microservice.hotel.repositories.HotelRepository;
import com.microservice.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


//     HotelServiceImpl(HotelRepository hotelRepository) {
//        this.hotelRepository = hotelRepository;
//    }





    @Override
    public Hotel create(Hotel hotel) {
//        String hotelId=UUID.randomUUID().toString();
//        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getSingle(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found"));
    }

    @Override
    public String delete(Long id) {
        hotelRepository.deleteById(id);
        return "hotel deleted" ;
    }
}
