package com.hotelservice.service;

import com.hotelservice.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    HotelDTO saveHotel(HotelDTO hotelDTO) throws IllegalAccessException, InstantiationException; //create user

    List<HotelDTO> getAllHotel() throws IllegalAccessException, InstantiationException; // get all user

    HotelDTO getHotelById(String hotelId) throws IllegalAccessException, InstantiationException; // get user by id

}
