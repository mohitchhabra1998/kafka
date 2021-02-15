package com.techprimers.kafka.springbootkafkaconsumerexample.Reader;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                try{
                    Long hotelId = Long.parseLong(data[0]);
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data[1]);
                    int roomCategoryId = Integer.parseInt(data[2]);
                    Map<String, Double> occupancyToPrice = new HashMap<>();

                    Integer data_size = data.length;
                    for(Integer data_index = 3; data_index < data_size; data_index++){

                        Integer occupancy = data_index-2;
                        String key = occupancy.toString();
                        Double value = Double.parseDouble(data[data_index]);
                        occupancyToPrice.put(key, value);

                        if(data_index == data_size-1){

                            while(occupancy < 3){
                                occupancy++;
                                key = occupancy.toString();
                                occupancyToPrice.put(key, value);
                            }

                        }

                    }

                    RoomPrice roomPrice = new RoomPrice(hotelId,date,roomCategoryId,occupancyToPrice);

                    kafkaTemplate.send(TOPIC, roomPrice);
                }catch (ParseException e){
                    System.out.println("Invalid to produce");
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       //new SimpleDateFormat("dd/MM/yyyy").parse(data[1])
    }
}
