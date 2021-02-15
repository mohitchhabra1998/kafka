package com.techprimers.kafka.springbootkafkaconsumerexample.dao;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.HotelRooms;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface HotelRoomsRepository extends CrudRepository<HotelRooms,Long> {
    @Query(value = "SELECT * FROM hotelrooms WHERE hotel_id=:hotelId AND room_category_id=:roomCategoryId AND occupancy=:occupancy", nativeQuery = true)
    public List<HotelRooms> get(long hotelId,int roomCategoryId, int occupancy);
}
