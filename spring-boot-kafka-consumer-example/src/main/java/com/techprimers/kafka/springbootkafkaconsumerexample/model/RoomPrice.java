package com.techprimers.kafka.springbootkafkaconsumerexample.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.techprimers.kafka.springbootkafkaconsumerexample.Converter.PgJsonbToMapConverter;
import com.techprimers.kafka.springbootkafkaconsumerexample.config.CustomJsonDateDeserializer;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="roomprice2")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RoomPrice implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    @Min(value = 1,message = "hotel_id should be greater than 0")
    @NotNull(message="Hotel Id is required")
    @Column(name="hotel_id")
    private long hotelId;

    @Column(name="date")
    @Basic
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;

    @Min(value = 1,message = "Room_Category Id should be greater than 0")
    @NotNull(message="Room_Category Id is required")
    @Column(name="room_category_id")
    private int roomCategoryId;

    @Column(name="otp",columnDefinition = "jsonb")
    //@Convert(converter = PgJsonbToMapConverter.class)
    @Type(type="jsonb")
    private Map<String,Double> occupancyToPrice;

    public RoomPrice() {
    }

    public RoomPrice(long hotelId, Date date, int roomCategoryId, Map<String,Double> occupancyToPrice) {
        this.hotelId = hotelId;
        this.date = date;
        this.roomCategoryId = roomCategoryId;
        this.occupancyToPrice=occupancyToPrice;
    }
    public RoomPrice(long id, long hotel_id, Date date, int room_category_id, Map<String,Double> occupancyToPrice) {
        this.id=id;
        this.hotelId = hotelId;
        this.date = date;
        this.roomCategoryId = roomCategoryId;
        this.occupancyToPrice=occupancyToPrice;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoomCategoryId(int roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public Map<String, Double> getOccupancyToPrice() {
        return occupancyToPrice;
    }

    public void setOccupancyToPrice(Map<String, Double> occupancyToPrice) {
        this.occupancyToPrice = occupancyToPrice;
    }

    @Override
    public String toString() {
        return "RoomPrice2{" +
                "id=" + id +
                ", hotel_id=" + hotelId +
                ", date=" + date +
                ", room_category_id=" + roomCategoryId +
                '}';
    }
}
