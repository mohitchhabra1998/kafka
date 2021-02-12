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
    @Query(value = "SELECT * FROM hotelrooms WHERE hotel_id=:hotel_id AND room_category_id=:room_category_id AND occupancy=:occupancy", nativeQuery = true)
    public List<HotelRooms> get(long hotel_id,int room_category_id, int occupancy);
}
