package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.techprimers.kafka.springbootkafkaconsumerexample.config.CustomJsonDateDeserializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name="roomprice")
public class RoomPrice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    @Column(name="hotel_id")
    private long hotel_id;

    @Column(name="date")
    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;

    @Min(value = 1,message = "Room_Category Id should be greater than 0")
    @NotNull(message="Room_Category Id is required")
    @Column(name="room_category_id")
    private int room_category_id;

    @Min(value = 1,message = "Occupancy should be greater than 0")
    @Max(value = 3,message = "Occupancy should be less than 3")
    @NotNull(message="Occupancy cannot be null")
    @Column(name="occupancy")
    private int occupancy;

    @Min(value = 0,message = "price should be greater than 0")
    @NotNull(message="Price should be entered")
    @Column(name="price")
    private double price;

    public RoomPrice() {

    }

    public RoomPrice(long hotel_id, Date date, int room_category_id, int occupancy) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public RoomPrice(long id, long hotel_id, Date date, int room_category_id, int occupancy) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(int room_category_id) {
        this.room_category_id = room_category_id;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomPrice{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", date='" + date + '\'' +
                ", room_category_id=" + room_category_id +
                ", occupancy=" + occupancy +
                ", price=" + price +
                '}';
    }
    public String keyForCache() {
        return this.hotel_id+"@"+this.room_category_id+"@"+this.date.toString()+"@"+this.occupancy;
    }
}
