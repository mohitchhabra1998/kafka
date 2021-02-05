package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("PRICE")
public class Price {
    private double price;

    public Price() {
    }

    public Price(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
