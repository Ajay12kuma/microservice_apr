package com.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedBack;

    private Hotel hotel;
}
