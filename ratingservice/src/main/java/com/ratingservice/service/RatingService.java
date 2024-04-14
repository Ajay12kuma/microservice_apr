package com.ratingservice.service;

import com.ratingservice.dto.RatingsDTO;
import com.ratingservice.entities.Ratings;

import java.util.List;

public interface RatingService {

    RatingsDTO creatRatings(RatingsDTO ratingsDTO) throws IllegalAccessException, InstantiationException;
    List<RatingsDTO> getRatings() throws IllegalAccessException, InstantiationException;
    List<RatingsDTO> getRatingsByUserId(String userId) throws IllegalAccessException, InstantiationException;
    List<RatingsDTO> getRatingByHotelId(String hotelId) throws IllegalAccessException, InstantiationException;


}
