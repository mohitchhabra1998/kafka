package com.techprimers.kafka.springbootkafkaconsumerexample.Consumer;

import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPrice2Repository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice2;
import com.techprimers.kafka.springbootkafkaconsumerexample.service.RoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class KafkaConsumer {
    @Autowired
    private RoomPriceService roomPriceService;

    @Autowired
    private RoomPrice2Repository roomPrice2Repository;

    @KafkaListener(topics="Kafka_Example2",groupId = "group_json",containerFactory="userKafkaListenerFactory")
    public void consumeJson(@Valid RoomPrice2 roomPrice2){
        try{
            System.out.println("Consumed JSON message "+roomPrice2);
            roomPrice2Repository.save(roomPrice2);
        }catch (Exception e){
            System.out.println("Invalid!!");
        }
    }
}
