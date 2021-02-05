package com.techprimers.kafka.springbootkafkaconsumerexample.model;

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
    private int id;


    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    @Column(name="hotel_id")
    private int hotel_id;

    @Size(min=1,message="Date cannot be empty")
    @NotBlank(message="Date is required")
    @Column(name="date")
    private String date;

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

    public RoomPrice(int hotel_id, String date, int room_category_id, int occupancy) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public RoomPrice(int id, int hotel_id, String date, int room_category_id, int occupancy) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        return this.hotel_id+"@"+this.room_category_id+"@"+this.date+"@"+this.occupancy;
    }
}
