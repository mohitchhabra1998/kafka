package com.techprimers.kafka.springbootkafkaconsumerexample.Reader;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class CsvReader {
    @Autowired
    private KafkaTemplate<String, RoomPrice> kafkaTemplate;

    private static final String TOPIC="Kafka_Example2";
    private String line="";

    public void produceCustomerData(){
        try{
            BufferedReader br=new BufferedReader(new FileReader("src/main/resources/roomprice.csv"));
            while((line=br.readLine())!=null){
                String[] data=line.split(",");
                RoomPrice rp=new RoomPrice(Integer.parseInt(data[0]),data[1],Integer.parseInt(data[2]),Integer.parseInt(data[3]));
                kafkaTemplate.send(TOPIC,rp);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
