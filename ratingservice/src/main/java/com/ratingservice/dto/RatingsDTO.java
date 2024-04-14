package com.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingsDTO {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedBack;
}
