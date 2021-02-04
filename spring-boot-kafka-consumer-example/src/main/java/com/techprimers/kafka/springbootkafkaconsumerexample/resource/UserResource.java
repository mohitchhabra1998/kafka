package com.techprimers.kafka.springbootkafkaconsumerexample.resource;


import com.techprimers.kafka.springbootkafkaconsumerexample.Reader.CsvReader;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class UserResource {
    private CsvReader csvReader;

    @Autowired
    public UserResource(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @GetMapping("/publish")
    public String post(){
        csvReader.produceCustomerData();
        return "published successfully";
    }

}
