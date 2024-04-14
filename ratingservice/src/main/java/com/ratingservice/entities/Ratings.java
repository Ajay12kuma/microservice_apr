package com.ratingservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings_t")
public class Ratings {
    @Id
    @Column(name = "rating_id")
    private String ratingId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "hotel_id")
    private String hotelId;
    @Column(name = "rating")
    private int rating;
    @Column(name = "feedback")
    private String feedBack;
}
