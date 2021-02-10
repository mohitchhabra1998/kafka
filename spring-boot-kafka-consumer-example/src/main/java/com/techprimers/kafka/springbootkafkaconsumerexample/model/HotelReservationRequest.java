package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.config.CustomJsonDateDeserializer;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;


@JsonNaming(value= PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HotelReservationRequest {

    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    private long hotel_id;


    @Basic
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;


    @NotNull(message="Room_Category Id is required")
    private String room_category;

    @Min(value = 1,message = "Occupancy should be greater than 0")
    @Max(value = 3,message = "Occupancy should be less than 3")
    @NotNull(message="Occupancy cannot be null")
    private int occupancy;

    private double price;
    public HotelReservationRequest() {
    }

    public HotelReservationRequest(long hotel_id, Date date, String room_category, int occupancy) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category = room_category;
        this.occupancy = occupancy;
    }

    public HotelReservationRequest(long hotel_id, Date date, String room_category, int occupancy,double price) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category = room_category;
        this.occupancy = occupancy;
        this.price=price;
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

    public String getRoom_category() {
        return room_category;
    }

    public void setRoom_category(String room_category) {
        this.room_category = room_category;
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

    public String keyForCache() {
        return this.hotel_id+"@"+ RoomCatEnum.valueOf(this.room_category).getValue() +"@"+this.date.toString()+"@"+this.occupancy;
    }
}
