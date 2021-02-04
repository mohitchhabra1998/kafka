package com.techprimers.kafka.springbootkafkaconsumerexample.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="roomprice")
public class RoomPrice {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="hotel_id")
    private int hotel_id;

    @Column(name="date")
    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="room_category_id")
    private int room_category_id;

    @Column(name="occupancy")
    private int occupancy;

    @Column(name="price")
    private double price;

    public RoomPrice() {

    }

    public RoomPrice(int hotel_id, Date date, int room_category_id, int occupancy) {
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public RoomPrice(int id, int hotel_id, Date date, int room_category_id, int occupancy) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.date = date;
        this.room_category_id = room_category_id;
        this.occupancy = occupancy;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
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

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomPrice{" +
                "id=" + id +
                ", hotel_id=" + hotel_id +
                ", date='" + date + '\'' +
                ", room_category_id=" + room_category_id +
                ", occupancy=" + occupancy +
                ", price=" + price +
                '}';
    }
}
