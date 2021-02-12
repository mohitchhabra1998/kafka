package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import java.util.Date;

public class RoomPriceCsv {
    private long hotel_id;
    private int room_category_id;
    private Date date;
    private double price1;
    private double price2;
    private double price3;

    public RoomPriceCsv() {
    }

    public RoomPriceCsv(long hotel_id, int room_category_id, Date date, double price1, double price2, double price3) {
        this.hotel_id = hotel_id;
        this.room_category_id = room_category_id;
        this.date = date;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getRoom_category_id() {
        return room_category_id;
    }

    public void setRoom_category_id(int room_category_id) {
        this.room_category_id = room_category_id;
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
