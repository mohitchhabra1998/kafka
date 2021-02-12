package com.techprimers.kafka.springbootkafkaconsumerexample.model;


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
    private long hotel_id;

    @Column(name="date")
    @Basic
    @Temporal(TemporalType.DATE)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;

    @NotNull(message="Room_Category Id is required")
    private String room_category;

    @Column(name="otp",columnDefinition = "jsonb")
    //@Convert(converter = PgJsonbToMapConverter.class)
    @Type(type="jsonb")
    private Map<String,Double> otp;

    public RoomPriceForUpdate() {
    }

    public RoomPriceForUpdate(long hotel_id, Date date, String room_category, Map<String,Double> otp) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category = room_category;
        this.otp=otp;
    }
    public RoomPriceForUpdate(long id, long hotel_id, Date date, String room_category, Map<String,Double> otp) {
        this.id=id;
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category = room_category;
        this.otp=otp;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom_category() {
        return this.room_category;
    }

    public void setRoom_category(String room_category) {
        this.room_category = room_category;
    }

    public Map<String, Double> getOtp() {
        return otp;
    }

    public void setOtp(Map<String,Double> otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "RoomPrice2{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", date=" + date +
                ", room_category_id=" + room_category +
                '}';
    }
    public String keyForCache() {
        return this.hotel_id+"@"+ RoomCatEnum.valueOf(this.room_category).getValue() +"@"+this.date.toString();
    }
}
