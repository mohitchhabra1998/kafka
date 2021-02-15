package com.techprimers.kafka.springbootkafkaconsumerexample.service;

import com.techprimers.kafka.springbootkafkaconsumerexample.dao.HotelRoomsRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomPriceForUpdate;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomsServiceImpl implements HotelRoomsService{
    @Autowired
    private HotelRoomsRepository hotelRoomsRepository;

    @Override
    public List<HotelRooms> get(long hotelId, int roomCategoryId, int occupancy) {
        return hotelRoomsRepository.get(hotelId,roomCategoryId,occupancy);
    }
}
