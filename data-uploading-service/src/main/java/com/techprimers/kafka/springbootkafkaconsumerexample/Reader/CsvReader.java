package com.techprimers.kafka.springbootkafkaconsumerexample.Reader;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice2;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPriceCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
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
    private KafkaTemplate<String, RoomPrice2> kafkaTemplate;

    private static final String TOPIC="Kafka_Example2";
    private String line="";

    public void produceCustomerData(){
        try{
            BufferedReader br=new BufferedReader(new FileReader("src/main/resources/roomprice.csv"));
            while((line=br.readLine())!=null){
                String[] data=line.split(",");
                try{
                    Long hotel_id = Long.parseLong(data[0]);
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data[1]);
                    int room_category_id = Integer.parseInt(data[2]);
                    Map<String, Double> occupancy_to_price = new HashMap<>();

                    Integer data_size = data.length;
                    for(Integer data_index = 3; data_index < data_size; data_index++){

                        Integer occupancy = data_index-2;
                        String key = occupancy.toString();
                        Double value = Double.parseDouble(data[data_index]);
                        occupancy_to_price.put(key, value);

                        if(data_index == data_size-1){

                            while(occupancy < 3){
                                occupancy++;
                                key = occupancy.toString();
                                occupancy_to_price.put(key, value);
                            }

                        }

                    }

                    RoomPrice2 roomPrice2 = new RoomPrice2(hotel_id,date,room_category_id,occupancy_to_price);

                    kafkaTemplate.send(TOPIC,roomPrice2);
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
