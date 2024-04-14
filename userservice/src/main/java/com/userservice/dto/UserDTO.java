package com.userservice.dto;

import com.userservice.entities.Ratings;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String userId;

    private String name;

    private String emailId;

    private String about;

    private List<Ratings> listofRating;
}
