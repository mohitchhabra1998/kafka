package com.techprimers.kafka.springbootkafkaconsumerexample.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

@Entity
@Table(name="roomprice2")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class RoomPrice2 implements Serializable {
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
    //@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date date;

    @Min(value = 1,message = "Room_Category Id should be greater than 0")
    @NotNull(message="Room_Category Id is required")
    @Column(name="room_category_id")
    private int room_category_id;

    @Column(name="otp",columnDefinition = "jsonb")
    //@Convert(converter = PgJsonbToMapConverter.class)
    @Type(type="jsonb")
    private Map<String,Double> otp;

    public RoomPrice2() {
    }

    public RoomPrice2(long hotel_id, Date date, int room_category_id, Map<String,Double> otp) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.otp=otp;
    }
    public RoomPrice2(long id, long hotel_id, Date date, int room_category_id, Map<String,Double> otp) {
        this.id=id;
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
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

    public int getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(int room_category_id) {
        this.room_category_id = room_category_id;
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
                ", room_category_id=" + room_category_id +
                '}';
    }
}
