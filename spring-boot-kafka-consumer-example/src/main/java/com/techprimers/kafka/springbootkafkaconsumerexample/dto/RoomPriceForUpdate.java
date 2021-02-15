package com.techprimers.kafka.springbootkafkaconsumerexample.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.techprimers.kafka.springbootkafkaconsumerexample.Enums.RoomCatEnum;
import com.techprimers.kafka.springbootkafkaconsumerexample.config.CustomJsonDateDeserializer;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RoomPriceForUpdate implements Serializable {
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

    @NotNull(message="Room_Category Id is required")
    private String roomCategory;

    @Column(name="otp",columnDefinition = "jsonb")
    //@Convert(converter = PgJsonbToMapConverter.class)
    @Type(type="jsonb")
    private Map<String,Double> occupancyToPrice;

    public RoomPriceForUpdate() {
    }

    public RoomPriceForUpdate(long hotelId, Date date, String roomCategory, Map<String,Double> occupancyToPrice) {
        this.hotelId = hotelId;
        this.date = date;
        this.roomCategory = roomCategory;
        this.occupancyToPrice=occupancyToPrice;
    }
    public RoomPriceForUpdate(long id, long hotel_id, Date date, String room_category, Map<String,Double> occupancyToPrice) {
        this.id=id;
        this.hotelId = hotelId;
        this.date = date;
        this.roomCategory = roomCategory;
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

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
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
                ", room_category_id=" + roomCategory +
                '}';
    }
    public String keyForCache() {
        return this.hotelId+"@"+ RoomCatEnum.valueOf(this.roomCategory).getValue() +"@"+this.date.toString();
    }
}
