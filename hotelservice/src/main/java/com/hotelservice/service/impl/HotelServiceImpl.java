package com.hotelservice.service.impl;

import com.hotelservice.dto.HotelDTO;
import com.hotelservice.entities.Hotel;
import com.hotelservice.exception.ResourceNotFoundException;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelDTO saveHotel(HotelDTO hotelDTO) {
        try {
            Hotel users = convertJSONtoEntity(hotelDTO, Hotel.class); // convert dto to entity
            Hotel savedUser = this.hotelRepository.save(users);
            return convertJSONtoEntity(savedUser, HotelDTO.class); //convert entity to dto
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while saving hotel: " + e.getMessage());
        }
    }

    @Override
    public List<HotelDTO> getAllHotel() {
        try {
            List<Hotel> listOfUser = this.hotelRepository.findAll();
            return convertListToDTO(listOfUser);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while getting all hotels: " + e.getMessage());
        }
    }

    @Override
    public HotelDTO getHotelById(String hotelId) {
        try {
            Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException(hotelId + " Not found..."));
            return convertJSONtoEntity(hotel, HotelDTO.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while getting hotel by ID: " + e.getMessage());
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

    private List<HotelDTO> convertListToDTO(List<Hotel> listOfUser) throws IllegalAccessException, InstantiationException {
        return listOfUser.stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
    }

    private HotelDTO convertUserToDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setLocation(hotel.getLocation());
        hotelDTO.setAbout(hotel.getAbout());
        return hotelDTO;
    }
}

