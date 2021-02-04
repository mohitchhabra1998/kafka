package com.techprimers.kafka.springbootkafkaconsumerexample.dao;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomPriceDao extends JpaRepository<RoomPrice,Integer> {

}
