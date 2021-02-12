package com.techprimers.kafka.springbootkafkaconsumerexample.model;

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
    private long hotel_id;

    @NotNull(message="Room_Category Id is required")
    private String room_category;

    @Basic
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;

    public RoomForDelete() {
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getRoom_category() {
        return room_category;
    }

    public void setRoom_category(String room_category) {
        this.room_category = room_category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String keyForCache() {
        return this.hotel_id+"@"+ RoomCatEnum.valueOf(this.room_category).getValue() +"@"+this.date.toString();
    }
}
