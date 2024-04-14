package com.userservice.external;

import com.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelServiceFeignClient {
    @GetMapping("/hotelservice/gethotelbyid/{hotelId}")
    Hotel gethotelDetail(@PathVariable String hotelId);
}
