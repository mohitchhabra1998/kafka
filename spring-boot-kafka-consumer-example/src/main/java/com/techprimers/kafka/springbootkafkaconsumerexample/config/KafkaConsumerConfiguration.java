package com.techprimers.kafka.springbootkafkaconsumerexample.config;


import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

 /*   @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);


        return new DefaultKafkaConsumerFactory<String, String>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory=new ConcurrentKafkaListenerContainerFactory<String,String>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }*/

    @Bean
    public ConsumerFactory<String, RoomPrice> userConsumerFactory(){
        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<String, RoomPrice>(config,new StringDeserializer(),new JsonDeserializer<>(RoomPrice.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RoomPrice> userKafkaListenerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,RoomPrice> factory=new ConcurrentKafkaListenerContainerFactory<String,RoomPrice>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
}
