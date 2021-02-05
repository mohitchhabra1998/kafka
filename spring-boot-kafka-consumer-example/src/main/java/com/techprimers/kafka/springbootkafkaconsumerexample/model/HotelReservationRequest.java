package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.*;
import java.util.Date;


@JsonNaming(value= PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HotelReservationRequest {

    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    private int hotel_id;

    @Size(min=1,message="Date cannot be empty")
    @NotBlank(message="Date is required")
    private String date;

    @Min(value = 1,message = "Room_Category Id should be greater than 0")
    @NotNull(message="Room_Category Id is required")
    private int room_category_id;

    @Min(value = 1,message = "Occupancy should be greater than 0")
    @Max(value = 3,message = "Occupancy should be less than 3")
    @NotNull(message="Occupancy cannot be null")
    private int occupancy;

    public HotelReservationRequest() {
    }

    public HotelReservationRequest(int hotel_id, String date, int room_category_id, int occupancy) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
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

    public String keyForCache() {
        return this.hotel_id+"@"+this.room_category_id+"@"+this.date+"@"+this.occupancy;
    }
}
