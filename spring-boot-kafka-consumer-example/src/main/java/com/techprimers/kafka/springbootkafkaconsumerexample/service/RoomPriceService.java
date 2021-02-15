package com.techprimers.kafka.springbootkafkaconsumerexample.service;

import com.techprimers.kafka.springbootkafkaconsumerexample.dto.HotelReservationRequest;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomForDelete;
import com.techprimers.kafka.springbootkafkaconsumerexample.dto.RoomPriceForUpdate;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;

import java.util.Date;
import java.util.List;

public interface RoomPriceService{
    public void save(RoomPrice roomPrice);
    public List<RoomPrice> findByCombo(long hotelId, Date date, int roomCategoryId);
    public int deleterow(Long hotelId, Date date, int roomCategoryId);
    public String delete(RoomForDelete roomForDelete);
    public String update(RoomPriceForUpdate roomPriceForUpdate);
    public Double get(HotelReservationRequest hotelReservationRequest);
}
