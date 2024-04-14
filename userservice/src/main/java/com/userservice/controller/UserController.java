package com.userservice.controller;

import com.userservice.dto.UserDTO;
import com.userservice.entities.User;
import com.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/userservice")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveuser") //http://localhost:8080/userservice/saveuser
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        try {
            UserDTO userDTO = this.userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("Error occurred while saving user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getalluser") //http://localhost:8080/userservice/getalluser
    public ResponseEntity<List<UserDTO>> getallUser() {
        try {
            List<UserDTO> listofusers = this.userService.getAllUser();
            return ResponseEntity.status(HttpStatus.OK).body(listofusers);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("Error occurred while getting all users: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getuserbyid/{id}") //http://localhost:8080/userservice/getuserbyid/1
    @CircuitBreaker(name = "calling_rating_hotel", fallbackMethod = "ratinghotelfallBackmethod")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        try {
            UserDTO userDTO = this.userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("Error occurred while getting user by ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<UserDTO> ratinghotelfallBackmethod(String id, Exception ex){
        log.info("Fallback method is executed because service is down: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                UserDTO.builder()
                        .emailId("dummy@gmail.com")
                        .name("Dummy")
                        .about("This user is created dummy because some service is down")
                        .userId("141234")
                        .build()
        );
    }
}

