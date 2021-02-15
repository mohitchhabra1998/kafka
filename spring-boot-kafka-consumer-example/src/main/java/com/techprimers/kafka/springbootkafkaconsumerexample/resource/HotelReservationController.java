package com.techprimers.kafka.springbootkafkaconsumerexample.resource;


import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.HotelRoomsRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.HotelReservationRequest;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomForDelete;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomPriceForUpdate;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.*;
import com.techprimers.kafka.springbootkafkaconsumerexample.service.HotelRoomsService;
import com.techprimers.kafka.springbootkafkaconsumerexample.service.RoomPriceService;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@EnableCaching
@CrossOrigin(origins="http://localhost:4200")
public class HotelReservationController {


    private static final double DEFAULT_PRICE=1500.0;

    @Autowired
    private RoomPriceService roomPriceService;

    @RequestMapping(value = "hotelreservations/price",method =RequestMethod.POST)
    public Double getHotelReservationPrice(@Valid @RequestBody HotelReservationRequest hotelReservationRequest){

        /*List<HotelRooms> li=hotelRoomsService.get(hotelReservationRequest.getHotelId(),
                RoomCatEnum.valueOf(hotelReservationRequest.getRoomCategory()).getValue(),
                hotelReservationRequest.getOccupancy());
        if(li.isEmpty()) throw new CombinationNotFoundException("Entered Combination does not exist");

        String cacheKey=hotelReservationRequest.keyForCache();
        String occupancy=Integer.toString(hotelReservationRequest.getOccupancy());

        if(map.containsKey(cacheKey)){
            Map<String,Double> op=map.get(cacheKey);
            if(op.containsKey(occupancy) && op.get(occupancy)!=null){
                System.out.println(op.get(occupancy)==null);
                return op.get(occupancy);
            }else{
                String comp = "0";
                Double ans = 0.0;
                for(String key : op.keySet()){
                    if(key.compareTo(comp) > 0){
                        comp = key;
                        ans =op.get(key);
                    }
                }
                System.out.println(ans+" hey");
                return ans;
            }
        }

        List<RoomPrice> li2=roomPriceService.findByCombo(hotelReservationRequest.getHotelId(),
                hotelReservationRequest.getDate(),
                RoomCatEnum.valueOf(hotelReservationRequest.getRoomCategory()).getValue());
        if(li2.isEmpty()){
            System.out.println("empty");
            return DEFAULT_PRICE;
        }
        Map<String,Double> prices=li2.get(0).getOccupancyToPrice();
        if(prices.containsKey(occupancy) && prices.get(occupancy)!=null){
            Double cache_value = prices.get(occupancy);
            map.put(cacheKey, prices);
            System.out.println(cache_value+"hry");
            return cache_value;
        }

        String comp = "0";
        Double ans = 0.0;
        for(String key : prices.keySet()){
            if(key.compareTo(comp) > 0){
                comp = key;
                ans = prices.get(key);
            }
        }
        map.put(cacheKey, prices);
        return ans;*/

        return roomPriceService.get(hotelReservationRequest);
    }


    @RequestMapping(value = "hotelreservations",method = RequestMethod.PUT)
    public String updateHotelReservation(@Valid @RequestBody RoomPriceForUpdate roomPriceForUpdate){
        /*if(roomPriceForUpdate.getOccupancyToPrice() == null|| roomPriceForUpdate.getOccupancyToPrice().isEmpty()){
            throw new BadlyFormatedException("Price for Occupancy 1 can not be null");
        }
        if(!validate(roomPriceForUpdate.getOccupancyToPrice())){
            throw new BadlyFormatedException("Prices of occupancies should be in Non-decreasing order");
        }
        int rows=roomPriceService.deleterow(roomPriceForUpdate.getHotelId(),roomPriceForUpdate.getDate(),
                RoomCatEnum.valueOf(roomPriceForUpdate.getRoomCategory()).getValue());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");
        RoomPrice roomPrice =new RoomPrice(roomPriceForUpdate.getHotelId(),roomPriceForUpdate.getDate(),
                RoomCatEnum.valueOf(roomPriceForUpdate.getRoomCategory()).getValue(),
                roomPriceForUpdate.getOccupancyToPrice());
        roomPriceService.save(roomPrice);

        String cacheKey=roomPriceForUpdate.keyForCache();
        map.put(cacheKey,roomPriceForUpdate.getOccupancyToPrice());
        return "Update Successfull";*/

        return roomPriceService.update(roomPriceForUpdate);
    }

    @RequestMapping(value="hotelreservations",method=RequestMethod.DELETE)
    public String deleteHotelReservation(@Valid @RequestBody RoomForDelete roomForDelete){

      /*  String cacheKey=roomForDelete.keyForCache();


        int rows=roomPriceService.deleterow(roomForDelete.getHotelId(),roomForDelete.getDate(),
                RoomCatEnum.valueOf(roomForDelete.getRoomCategory()).getValue());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");

        map.remove(cacheKey);
        return "delete Successfull";*/

        return roomPriceService.delete(roomForDelete);
    }
}
