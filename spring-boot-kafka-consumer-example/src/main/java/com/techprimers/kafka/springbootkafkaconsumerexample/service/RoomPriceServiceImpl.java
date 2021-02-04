package com.techprimers.kafka.springbootkafkaconsumerexample.service;

import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceDao;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomPriceServiceImpl implements RoomPriceService{
    private RoomPriceDao roomPriceDao;


    @Autowired
    public RoomPriceServiceImpl(RoomPriceDao roomPriceDao) {
        this.roomPriceDao = roomPriceDao;
    }

    @Override
    public void save(RoomPrice roomPrice) {
        roomPriceDao.save(roomPrice);
    }
}
