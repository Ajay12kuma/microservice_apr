package com.ratingservice.service.impl;

import com.ratingservice.dto.RatingsDTO;
import com.ratingservice.entities.Ratings;
import com.ratingservice.repository.RatingRepository;
import com.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingsServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public RatingsDTO creatRatings(RatingsDTO ratingsDTO) {
        try {
            Ratings ratings = convertJSONtoEntity(ratingsDTO, Ratings.class);
            Ratings save = this.ratingRepository.save(ratings);
            return convertJSONtoEntity(save, RatingsDTO.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while creating ratings: " + e.getMessage());
        }
    }

    @Override
    public List<RatingsDTO> getRatings() {
        try {
            List<Ratings> listOfRatings = this.ratingRepository.findAll();
            return convertListToDTO(listOfRatings);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while getting all ratings: " + e.getMessage());
        }
    }

    @Override
    public List<RatingsDTO> getRatingsByUserId(String userId) {
        try {
            List<Ratings> byUserId = this.ratingRepository.findByuserId(userId);
            return convertListToDTO(byUserId);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while getting ratings by user ID: " + e.getMessage());
        }
    }

    @Override
    public List<RatingsDTO> getRatingByHotelId(String hotelId) {
        try {
            List<Ratings> byHotelId = this.ratingRepository.findByhotelId(hotelId);
            return convertListToDTO(byHotelId);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while getting ratings by hotel ID: " + e.getMessage());
        }
    }

    public static <T> T convertJSONtoEntity(Object source, Class<T> targetType) throws IllegalAccessException, InstantiationException {
        T target = targetType.newInstance();
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetType.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (sourceField.getName().equals(targetField.getName()) &&
                        sourceField.getType().equals(targetField.getType())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    targetField.set(target, sourceField.get(source));
                }
            }
        }

        return target;
    }

    private List<RatingsDTO> convertListToDTO(List<Ratings> listOfRatings) throws IllegalAccessException, InstantiationException {
        return listOfRatings.stream()
                .map(this::convertRatingToDTO)
                .collect(Collectors.toList());
    }

    private RatingsDTO convertRatingToDTO(Ratings rating) {
        RatingsDTO ratingsDTO = new RatingsDTO();
        ratingsDTO.setRatingId(rating.getRatingId());
        ratingsDTO.setUserId(rating.getUserId());
        ratingsDTO.setRating(rating.getRating());
        ratingsDTO.setFeedBack(rating.getFeedBack());
        ratingsDTO.setHotelId(rating.getHotelId());
        return ratingsDTO;
    }
}

