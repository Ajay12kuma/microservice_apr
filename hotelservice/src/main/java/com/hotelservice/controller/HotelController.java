package com.hotelservice.controller;

import com.hotelservice.dto.HotelDTO;
import com.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelservice")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/savehotel") //http://localhost:8081/hotelservice/savehotel
    public ResponseEntity<HotelDTO> createUser(@RequestBody HotelDTO hotel) {
        try {
            HotelDTO hotelDTO = this.hotelService.saveHotel(hotel);
            return ResponseEntity.status(HttpStatus.OK).body(hotelDTO);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getallhotel") //http://localhost:8081/hotelservice/getallhotel
    public ResponseEntity<List<HotelDTO>> getallUser() {
        try {
            List<HotelDTO> listofhotel = this.hotelService.getAllHotel();
            return ResponseEntity.status(HttpStatus.OK).body(listofhotel);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/gethotelbyid/{id}") //http://localhost:8081/hotelservice/gethotelbyid/1
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable String id) {
        try {
            HotelDTO hotelDTO = this.hotelService.getHotelById(id);
            if (hotelDTO != null) {
                return ResponseEntity.status(HttpStatus.OK).body(hotelDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

