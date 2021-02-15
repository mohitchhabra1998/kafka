package com.techprimers.kafka.springbootkafkaconsumerexample.dto;

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


//@JsonNaming(value= PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HotelReservationRequest {

    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    private long hotelId;


    @Basic
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;


    @NotNull(message="Room_Category Id is required")
    private String roomCategory;

    @Min(value = 1,message = "Occupancy should be greater than 0")
    @Max(value = 3,message = "Occupancy should be less than 3")
    @NotNull(message="Occupancy cannot be null")
    private int occupancy;

  //  private double price;
    public HotelReservationRequest() {
    }

    public HotelReservationRequest(long hotelId, Date date, String roomCategory, int occupancy) {
        this.hotelId = hotelId;
        this.date = date;
        this.roomCategory = roomCategory;
        this.occupancy = occupancy;
    }

   /* public HotelReservationRequest(long hotelId, Date date, String roomCategory, int occupancy,double price) {
        this.hotelId = hotelId;
        this.date = date;
        this.roomCategory = roomCategory;
        this.occupancy = occupancy;
        this.price=price;
    }*/
    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    /*public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }*/

    public String keyForCache() {
        return this.hotelId+"@"+ RoomCatEnum.valueOf(this.roomCategory).getValue() +"@"+this.date.toString();
    }
}
