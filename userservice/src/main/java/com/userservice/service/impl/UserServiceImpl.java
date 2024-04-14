package com.userservice.service.impl;

import com.userservice.dto.UserDTO;
import com.userservice.entities.Hotel;
import com.userservice.entities.Ratings;
import com.userservice.entities.User;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.userservice.external.HotelServiceFeignClient;
import java.lang.reflect.Field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelServiceFeignClient hotelServiceFeignClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDTO saveUser(UserDTO user) {
        try {
            User users = convertJSONtoEntity(user, User.class); // convert dto to entity
            User savedUser = this.userRepository.save(users);
            return convertJSONtoEntity(savedUser, UserDTO.class); //convert entity to dto
        } catch (IllegalAccessException | InstantiationException e) {
            LOGGER.error("Error occurred while saving user: " + e.getMessage());
            throw new RuntimeException("Error occurred while saving user: " + e.getMessage());
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        try {
            List<User> listOfUser = this.userRepository.findAll();
            return convertListToDTO(listOfUser);
        } catch (IllegalAccessException | InstantiationException e) {
            LOGGER.error("Error occurred while getting all users: " + e.getMessage());
            throw new RuntimeException("Error occurred while getting all users: " + e.getMessage());
        }
    }

    @Override
    public UserDTO getUserById(String userId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId + " Not found..."));

            Ratings[] ratingByuser = restTemplate.getForObject("http://RATINGSERVICE/ratingservice/getratingsbyuserid/" + userId, Ratings[].class);
            List<Ratings> listofratings = Arrays.asList(ratingByuser);
            LOGGER.info(listofratings.toString());
            listofratings.forEach(ratings -> {
                Hotel hotel = hotelServiceFeignClient.gethotelDetail(ratings.getHotelId());
                ratings.setHotel(hotel);
            });

            user.setListofRating(listofratings);
            return convertJSONtoEntity(user, UserDTO.class);
        } catch (IllegalAccessException | InstantiationException e) {
            LOGGER.error("Error occurred while getting user by ID: " + e.getMessage());
            throw new RuntimeException("Error occurred while getting user by ID: " + e.getMessage());
        }
    }

    public static <T> T convertJSONtoEntity(Object source, Class<T> targetType) throws IllegalAccessException, InstantiationException {
        T target = targetType.newInstance();
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetType.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (sourceField.getName().equals(targetField.getName()) &&
                        sourceField.getType().equals(targetField.getType())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    targetField.set(target, sourceField.get(source));
                }
            }
        }

        return target;
    }

    private List<UserDTO> convertListToDTO(List<User> listOfUser) throws IllegalAccessException, InstantiationException {
        return listOfUser.stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}

