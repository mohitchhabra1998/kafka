package com.techprimers.kafka.springbootkafkaconsumerexample.resource;


import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelReservationRequest;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableCaching
@CrossOrigin(origins="http://localhost:4200")
public class HotelReservationController {

    private static final String HASH="PRICE";
    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @PostMapping("hotelreservations")
    public void createHotelReservation(@Valid @RequestBody RoomPrice roomPrice){
        roomPriceRepository.save(roomPrice);
    }

    @PostMapping("hotelreservations/price")
    @Cacheable(key="#hotelReservationRequest.keyForCache()",value = HASH)
    public Double getHotelReservationPrice(@Valid @RequestBody HotelReservationRequest hotelReservationRequest){
        List<Double> prices = roomPriceRepository.getPrice(hotelReservationRequest.getHotel_id(),
                hotelReservationRequest.getDate(), RoomCatEnum.valueOf(hotelReservationRequest.getRoom_category()).getValue(),
                hotelReservationRequest.getOccupancy());
        if(prices.isEmpty()) throw new CombinationNotFoundException("Entered Combination does not exist");
        return prices.get(0);
    }

    @PutMapping("hotelreservations")
    @CachePut(key="#hotelReservationRequest.keyForCache()",value=HASH)
    public Double updateHotelReservation(@Valid @RequestBody HotelReservationRequest hotelReservationRequest){

        int rows=roomPriceRepository.updatePrice(hotelReservationRequest.getHotel_id(),
                hotelReservationRequest.getDate(),
                RoomCatEnum.valueOf(hotelReservationRequest.getRoom_category()).getValue(),
                hotelReservationRequest.getOccupancy(), hotelReservationRequest.getPrice());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");
        return hotelReservationRequest.getPrice();
    }

    @DeleteMapping("hotelreservations")
    @CacheEvict(key="#hotelReservationRequest.keyForCache()",value=HASH)
    public String deleteHotelReservation(@Valid @RequestBody  HotelReservationRequest hotelReservationRequest){

        int rows=roomPriceRepository.deleterow(hotelReservationRequest.getHotel_id(),
                hotelReservationRequest.getDate(),
                RoomCatEnum.valueOf(hotelReservationRequest.getRoom_category()).getValue(),
                hotelReservationRequest.getOccupancy());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");
        return "delete Successfull";
    }


}
