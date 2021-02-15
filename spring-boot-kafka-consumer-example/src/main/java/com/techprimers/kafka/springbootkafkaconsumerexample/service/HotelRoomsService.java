package com.techprimers.kafka.springbootkafkaconsumerexample.service;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelRooms;

import java.util.List;

public interface HotelRoomsService {
    public List<HotelRooms> get(long hotelId, int roomCategoryId, int occupancy);
}
