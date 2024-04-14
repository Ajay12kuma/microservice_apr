package com.ratingservice.repository;

import com.ratingservice.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RatingRepository extends JpaRepository<Ratings,String> {
    List<Ratings> findByuserId(String usserId);
    List<Ratings> findByhotelId(String hotelId);

}
