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
    private long hotelId;


    @Min(value = 1,message = "Room_Category Id should be greater than 0")
    @NotNull(message="Room_Category Id is required")
    @Column(name="room_category_id")
    private int roomCategoryId;


    @Min(value = 1,message = "Occupancy should be greater than 0")
    @Max(value = 3,message = "Occupancy should be less than 3")
    @NotNull(message="Occupancy cannot be null")
    @Column(name="occupancy")
    private int occupancy;

    public HotelRooms() {
    }

    public HotelRooms(long id,long hotelId,int roomCategoryId,int occupancy){
        this.id=id;
        this.hotelId=hotelId;
        this.roomCategoryId=roomCategoryId;
        this.occupancy=occupancy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(int roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }
}
