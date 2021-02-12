package com.techprimers.kafka.springbootkafkaconsumerexample;

import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.dao.RoomPrice2Repository;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootKafkaConsumerExampleApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaConsumerExampleApplication.class, args);
	}

}
