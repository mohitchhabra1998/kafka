package com.techprimers.kafka.springbootkafkaconsumerexample.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.config.CustomJsonDateDeserializer;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RoomForDelete {

    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    private long hotelId;

    @NotNull(message="Room_Category Id is required")
    private String roomCategory;

    @Basic
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;

    public RoomForDelete() {
    }

    public RoomForDelete(long hotelId, String roomCategory, Date date) {
        this.hotelId = hotelId;
        this.roomCategory = roomCategory;
        this.date = date;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String keyForCache() {
        return this.hotelId+"@"+ RoomCatEnum.valueOf(this.roomCategory).getValue() +"@"+this.date.toString();
    }
}
