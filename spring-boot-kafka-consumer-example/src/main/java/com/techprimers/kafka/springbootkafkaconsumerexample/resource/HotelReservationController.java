package com.techprimers.kafka.springbootkafkaconsumerexample.resource;


import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.HotelRoomsRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPrice2Repository;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.*;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@EnableCaching
@CrossOrigin(origins="http://localhost:4200")
public class HotelReservationController {

    private static final String HASH="PRICE";

    private static final double DEFAULT_PRICE=1500.0;
    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Autowired
    private HotelRoomsRepository hotelRoomsRepository;

    @Autowired
    private RoomPrice2Repository roomPrice2Repository;

    RedissonClient reddisson = Redisson.create();
    RMap<String, Map<String, Double>> map = reddisson.getMap("myMap");

    @PostMapping("hotelreservations")
    public void createHotelReservation(@Valid @RequestBody RoomPrice roomPrice){
        roomPriceRepository.save(roomPrice);
    }

    @PostMapping("hotelreservations/price")
    //@Cacheable(key="#hotelReservationRequest.keyForCache()",value = HASH)
    public Double getHotelReservationPrice(@Valid @RequestBody HotelReservationRequest hotelReservationRequest){

       /* List<HotelRooms> li=hotelRoomsRepository.get(hotelReservationRequest.getHotel_id(),
                RoomCatEnum.valueOf(hotelReservationRequest.getRoom_category()).getValue(),
                hotelReservationRequest.getOccupancy());
        if(li.isEmpty()) throw new CombinationNotFoundException("Entered Combination does not exist");*/
        /*List<Double> prices = roomPriceRepository.getPrice(hotelReservationRequest.getHotel_id(),
                hotelReservationRequest.getDate(), RoomCatEnum.valueOf(hotelReservationRequest.getRoom_category()).getValue(),
                hotelReservationRequest.getOccupancy());
        if(prices.isEmpty()) return DEFAULT_PRICE;
        return prices.get(0);*/


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

        List<RoomPrice2> li2=roomPrice2Repository.findByCombo(hotelReservationRequest.getHotel_id(),
                hotelReservationRequest.getDate(),
                RoomCatEnum.valueOf(hotelReservationRequest.getRoom_category()).getValue());
        if(li2.isEmpty()){
            System.out.println("empty");
            return DEFAULT_PRICE;
        }
        Map<String,Double> prices=li2.get(0).getOtp();
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
        return ans;
    }

    @PutMapping("hotelreservations")
    //@CachePut(key="#hotelReservationRequest.keyForCache()",value=HASH)
    public String updateHotelReservation(@Valid @RequestBody RoomPriceForUpdate roomPriceForUpdate){
        if(roomPriceForUpdate.getOtp().isEmpty()){
            throw new BadlyFormatedException("Price for Occupancy 1 can not be null");
        }
        if(!validate(roomPriceForUpdate.getOtp())){
            throw new BadlyFormatedException("Prices of occupancies should be in Non-decreasing order");
        }
        System.out.println(roomPriceForUpdate.getOtp().toString());
        int rows=roomPrice2Repository.deleterow(roomPriceForUpdate.getHotel_id(),roomPriceForUpdate.getDate(),
                RoomCatEnum.valueOf(roomPriceForUpdate.getRoom_category()).getValue());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");
        RoomPrice2 roomPrice2=new RoomPrice2(roomPriceForUpdate.getHotel_id(),roomPriceForUpdate.getDate(),
                RoomCatEnum.valueOf(roomPriceForUpdate.getRoom_category()).getValue(),
                roomPriceForUpdate.getOtp());
        roomPrice2Repository.save(roomPrice2);

        String cacheKey=roomPriceForUpdate.keyForCache();
        map.put(cacheKey,roomPriceForUpdate.getOtp());
        return "Update Successfull";
    }

    @DeleteMapping("hotelreservations")
    //@CacheEvict(key="#hotelReservationRequest.keyForCache()",value=HASH)
    public String deleteHotelReservation(@Valid @RequestBody RoomForDelete roomForDelete){

        String cacheKey=roomForDelete.keyForCache();
        map.remove(cacheKey);

        int rows=roomPrice2Repository.deleterow(roomForDelete.getHotel_id(),roomForDelete.getDate(),
                RoomCatEnum.valueOf(roomForDelete.getRoom_category()).getValue());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");
        return "delete Successfull";
    }

    @GetMapping("/demo")
    public String getMap(){
        List<RoomPrice2> lis=roomPrice2Repository.findById(1);
        return lis.get(0).getOtp().toString();
    }

    public boolean validate(Map<String,Double> map){
        double f=-1.0;
        for(Map.Entry<String,Double> e: map.entrySet()){
            double val=e.getValue();
            if(val<f) return false;
            f=val;
        }
        return true;
    }
}
