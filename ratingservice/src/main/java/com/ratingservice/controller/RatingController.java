package com.ratingservice.controller;

import com.ratingservice.dto.RatingsDTO;
import com.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratingservice")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping("/createratings") //http://localhost:8082/ratingservice/createratings
    public ResponseEntity<RatingsDTO> creatrRating(@RequestBody RatingsDTO ratingsDTO) {
        try {
            RatingsDTO ratingsCreated = this.ratingService.creatRatings(ratingsDTO);
            return ResponseEntity.status(HttpStatus.OK).body(ratingsCreated);
        } catch (IllegalAccessException | InstantiationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getallratings") //http://localhost:8082/ratingservice/getallratings
    public ResponseEntity<List<RatingsDTO>> getAllRatings() {
        try {
            List<RatingsDTO> getAllRatings = this.ratingService.getRatings();
            return ResponseEntity.status(HttpStatus.OK).body(getAllRatings);
        } catch (IllegalAccessException | InstantiationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getratingsbyuserid/{userId}") //http://localhost:8082/ratingservice/getratingsbyuserid/1
    public ResponseEntity<List<RatingsDTO>> getratingsByuserId(@PathVariable String userId) {
        try {
            List<RatingsDTO> getAllRatings = this.ratingService.getRatingsByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(getAllRatings);
        } catch (IllegalAccessException | InstantiationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/getratingsbyhotelid/{hotelId}") //http://localhost:8082/ratingservice/getratingsbyhotelid/1
    public ResponseEntity<List<RatingsDTO>> getratingsByHotelId(@PathVariable String hotelId) {
        try {
            List<RatingsDTO> getAllRatings = this.ratingService.getRatingByHotelId(hotelId);
            return ResponseEntity.status(HttpStatus.OK).body(getAllRatings);
        } catch (IllegalAccessException | InstantiationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
