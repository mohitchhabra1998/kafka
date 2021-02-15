package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import java.util.Date;

public class RoomPriceCsv {
    private long hotelId;
    private int roomCategoryId;
    private Date date;
    private double price1;
    private double price2;
    private double price3;

    public RoomPriceCsv() {
    }

    public RoomPriceCsv(long hotelId, int roomCategoryId, Date date, double price1, double price2, double price3) {
        this.hotelId = hotelId;
        this.roomCategoryId = roomCategoryId;
        this.date = date;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotel_id(long hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomCategoryId() {
        return roomCategoryId;
    }

    public void setRoom_category_id(int roomCategoryId) {
        this.roomCategoryId = roomCategoryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public double getPrice3() {
        return price3;
    }

    public void setPrice3(double price3) {
        this.price3 = price3;
    }
}
