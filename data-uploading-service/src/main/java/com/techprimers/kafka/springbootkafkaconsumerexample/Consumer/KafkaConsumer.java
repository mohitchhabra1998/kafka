package com.techprimers.kafka.springbootkafkaconsumerexample.Consumer;

import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPriceRepository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
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
    private RoomPriceRepository roomPriceRepository;

    @KafkaListener(topics="Kafka_Example2",groupId = "group_json",containerFactory="userKafkaListenerFactory")
    public void consumeJson(@Valid RoomPrice roomPrice){
        try{
            System.out.println("Consumed JSON message "+ roomPrice);
            roomPriceRepository.save(roomPrice);
        }catch (Exception e){
            System.out.println("Invalid!!");
        }
    }
}
