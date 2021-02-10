package com.techprimers.kafka.springbootkafkaconsumerexample.service;

import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomPriceServiceImpl implements RoomPriceService{
    private RoomPriceRepository roomPriceRepository;


    @Autowired
    public RoomPriceServiceImpl(RoomPriceRepository roomPriceRepository) {
        this.roomPriceRepository = roomPriceRepository;
    }

    @Override
    public void save(RoomPrice roomPrice) {
        roomPriceRepository.save(roomPrice);
    }
}
