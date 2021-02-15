package com.techprimers.kafka.springbootkafkaconsumerexample.service;

import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.HotelReservationRequest;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomForDelete;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomPriceForUpdate;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelRooms;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import com.techprimers.kafka.springbootkafkaconsumerexample.resource.BadlyFormatedException;
import com.techprimers.kafka.springbootkafkaconsumerexample.resource.CombinationNotFoundException;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoomPriceServiceImpl implements RoomPriceService{
    private static final double DEFAULT_PRICE=1500.0;

    RedissonClient reddisson = Redisson.create();
    RMap<String, Map<String, Double>> map = reddisson.getMap("myMap");

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Autowired
    private HotelRoomsService hotelRoomsService;

    @Override
    public void save(RoomPrice roomPrice){
        roomPriceRepository.save(roomPrice);
    }

    @Override
    public List<RoomPrice> findByCombo(long hotelId, Date date, int roomCategoryId){
        return roomPriceRepository.findByCombo(hotelId,date,roomCategoryId);
    }

    @Override
    public int deleterow(Long hotelId, Date date, int roomCategoryId){
        return roomPriceRepository.deleterow(hotelId,date,roomCategoryId);
    }

    @Override
    public String delete(RoomForDelete roomForDelete){

        String cacheKey=roomForDelete.keyForCache();


        int rows=roomPriceRepository.deleterow(roomForDelete.getHotelId(),roomForDelete.getDate(),
                RoomCatEnum.valueOf(roomForDelete.getRoomCategory()).getValue());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");

        map.remove(cacheKey);
        return "delete Successfull";
    }

    @Override
    public String update(RoomPriceForUpdate roomPriceForUpdate) {
        if(roomPriceForUpdate.getOccupancyToPrice() == null|| roomPriceForUpdate.getOccupancyToPrice().isEmpty()){
            throw new BadlyFormatedException("Price for Occupancy 1 can not be null");
        }
        if(!validate(roomPriceForUpdate.getOccupancyToPrice())){
            throw new BadlyFormatedException("Prices of occupancies should be in Non-decreasing order");
        }
        int rows=roomPriceRepository.deleterow(roomPriceForUpdate.getHotelId(),roomPriceForUpdate.getDate(),
                RoomCatEnum.valueOf(roomPriceForUpdate.getRoomCategory()).getValue());
        if(rows==0) throw new CombinationNotFoundException("Record does not exist!!");
        RoomPrice roomPrice =new RoomPrice(roomPriceForUpdate.getHotelId(),roomPriceForUpdate.getDate(),
                RoomCatEnum.valueOf(roomPriceForUpdate.getRoomCategory()).getValue(),
                roomPriceForUpdate.getOccupancyToPrice());
        roomPriceRepository.save(roomPrice);

        String cacheKey=roomPriceForUpdate.keyForCache();
        map.put(cacheKey,roomPriceForUpdate.getOccupancyToPrice());
        return "Update Successfull";
    }

    @Override
    public Double get(HotelReservationRequest hotelReservationRequest) {
        List<HotelRooms> li=hotelRoomsService.get(hotelReservationRequest.getHotelId(),
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

        List<RoomPrice> li2=roomPriceRepository.findByCombo(hotelReservationRequest.getHotelId(),
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
        return ans;
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
