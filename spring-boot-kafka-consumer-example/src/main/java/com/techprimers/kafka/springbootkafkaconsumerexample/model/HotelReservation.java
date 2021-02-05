package com.techprimers.kafka.springbootkafkaconsumerexample.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "hotelreservations")
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "hotel_id")
    private long hotel_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "room_category_id")
    private String room_category_id;

    @Column(name = "occupancy")
    private int occupancy;

    @Column(name = "price")
    private long price;


    public HotelReservation() {

    }

    public HotelReservation(long hotel_id, Date date, String room_category_id, int occupancy) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public HotelReservation(long hotel_id, Date date, String room_category_id, int occupancy, long price) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
        this.price = price;
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

    public String getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(String room_category_id) {
        this.room_category_id = room_category_id;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }



    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Hotelid")
    private long hotel_id;

    @Column(name = "Date")
    private String date;

    @Column(name = "Roomcategoryid")
    private String room_category_id;

    @Column(name = "Price")
    Type( type = "json" )
    private Map<Integer, Long> price = new HashMap<Integer, Long>();


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(String room_category_id) {
        this.room_category_id = room_category_id;
    }

    public Map<Integer, Long> getPrice() {
        return price;
    }

    public void setPrice(Map<Integer, Long> price) {
        this.price = price;
    }*/


}
