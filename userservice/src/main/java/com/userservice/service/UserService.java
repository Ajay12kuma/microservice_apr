package com.userservice.service;

import com.userservice.dto.UserDTO;
import com.userservice.entities.User;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO user) throws IllegalAccessException, InstantiationException; //create user


    List<UserDTO> getAllUser() throws IllegalAccessException, InstantiationException; // get all user

    UserDTO getUserById(String userId) throws IllegalAccessException, InstantiationException; // get user by id
}
