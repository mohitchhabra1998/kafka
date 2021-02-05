package com.techprimers.kafka.springbootkafkaconsumerexample.resource;


import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelReservation;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelReservationRequest;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableCaching
public class HotelReservationController {

    private static final String HASH="PRICE";
    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @PostMapping("hotelreservations")
    public void createHotelReservation(@Valid @RequestBody RoomPrice roomPrice){
        roomPriceRepository.save(roomPrice);
    }

    @GetMapping("hotelreservations")
    @Cacheable(key="#hotelReservationRequest.keyForCache()",value = HASH)
    public Double getHotelReservationPrice(@Valid @RequestBody HotelReservationRequest hotelReservationRequest){
        List<Double> prices = roomPriceRepository.getPrice(hotelReservationRequest.getHotel_id(),
                hotelReservationRequest.getDate(), hotelReservationRequest.getRoom_category_id(),
                hotelReservationRequest.getOccupancy());
        if(prices.isEmpty()) throw new CombinationNotFoundException("Entered Combination does not exist");
        return prices.get(0);
    }

    @PutMapping("hotelreservations")
    @CachePut(key="#roomPrice.keyForCache()",value=HASH)
    public Double updateHotelReservation(@Valid @RequestBody RoomPrice roomPrice){

        roomPriceRepository.updatePrice(roomPrice.getHotel_id(), roomPrice.getDate(),
                roomPrice.getRoom_category_id(), roomPrice.getOccupancy(), roomPrice.getPrice());
        return roomPrice.getPrice();
    }

    @DeleteMapping("hotelreservations")
    @CacheEvict(key="#roomPrice.keyForCache()",value=HASH)
    public String deleteHotelReservation(@Valid @RequestBody RoomPrice roomPrice){

        roomPriceRepository.deleterow(roomPrice.getHotel_id(), roomPrice.getDate(),
                roomPrice.getRoom_category_id(), roomPrice.getOccupancy());

        return "delete Successfull";
    }


}
