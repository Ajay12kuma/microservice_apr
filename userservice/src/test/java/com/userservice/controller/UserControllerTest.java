package com.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.userservice.dto.UserDTO;
import com.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() throws IllegalAccessException, InstantiationException {
        // Given
        UserDTO user = new UserDTO(); // create a sample user
        when(userService.saveUser(user)).thenReturn(user);

        // When
        ResponseEntity<UserDTO> response = userController.createUser(user);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    public void testGetAllUser() throws IllegalAccessException, InstantiationException {
        // Given
        UserDTO user1 = new UserDTO();
        UserDTO user2 = new UserDTO();
        List<UserDTO> users = Arrays.asList(user1, user2);
        when(userService.getAllUser()).thenReturn(users);

        // When
        ResponseEntity<List<UserDTO>> response = userController.getallUser();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userService, times(1)).getAllUser();
    }

    @Test
    public void testGetUserById() throws IllegalAccessException, InstantiationException {
        // Given
        String userId = "1";
        UserDTO user = new UserDTO();
        when(userService.getUserById(userId)).thenReturn(user);

        // When
        UserDTO response = userController.getUserById(userId);

        // Then
        assertEquals(user, response);
        verify(userService, times(1)).getUserById(userId);
    }
}
