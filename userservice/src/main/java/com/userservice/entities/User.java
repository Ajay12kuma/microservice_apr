package com.userservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_t")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Column(name = "user_id")
    @Id
    private String userId;
    @Column(name = "name",length = 20)
    private String name;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "about")
    private String about;
    @Transient
    private List<Ratings> listofRating = new ArrayList<>();

}
