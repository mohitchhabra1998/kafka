package com.techprimers.kafka.springbootkafkaconsumerexample.Consumer;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import com.techprimers.kafka.springbootkafkaconsumerexample.service.RoomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    private RoomPriceService roomPriceService;

    @KafkaListener(topics="Kafka_Example2",groupId = "group_json",containerFactory="userKafkaListenerFactory")
    public void consumeJson(RoomPrice roomPrice){
        System.out.println("Consumed JSON message "+roomPrice);
        roomPriceService.save(roomPrice);
    }
}
