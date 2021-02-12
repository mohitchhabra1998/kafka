package com.techprimers.kafka.springbootkafkaconsumerexample.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.techprimers.kafka.springbootkafkaconsumerexample.config.CustomJsonDateDeserializer;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="hotelrooms")
public class HotelRooms {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    @Column(name="hotel_id")
    private long hotel_id;


    @Min(value = 1,message = "Room_Category Id should be greater than 0")
    @NotNull(message="Room_Category Id is required")
    @Column(name="room_category_id")
    private int room_category_id;


    @Min(value = 1,message = "Occupancy should be greater than 0")
    @Max(value = 3,message = "Occupancy should be less than 3")
    @NotNull(message="Occupancy cannot be null")
    @Column(name="occupancy")
    private int occupancy;

    public HotelRooms() {
    }

    public HotelRooms(long id,long hotel_id,int room_category_id,int occupancy){
        this.id=id;
        this.hotel_id=hotel_id;
        this.room_category_id=room_category_id;
        this.occupancy=occupancy;
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
}
