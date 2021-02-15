package com.techprimers.kafka.springbootkafkaconsumerexample.dao;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface RoomPriceRepository extends CrudRepository<RoomPrice,Long> {
    @Query(value = "SELECT * FROM roomprice2 WHERE id=:id", nativeQuery = true)
    public List<RoomPrice> findById(long id);

    @Query(value = "SELECT * FROM roomprice2 WHERE hotel_id=:hotelId AND date=:date AND room_category_id=:roomCategoryId", nativeQuery = true)
    public List<RoomPrice> findByCombo(long hotelId, Date date, int roomCategoryId);

    @Modifying
    @Query(value = "DELETE FROM roomprice2 WHERE hotel_id=:hotelId AND date=:date AND room_category_id=:roomCategoryId", nativeQuery = true)
    public int deleterow(long hotelId,Date date, int roomCategoryId);

    @Modifying
    @Query(value = "UPDATE roomprice2 SET otp=:occupancyToPrice WHERE hotel_id=:hotelId AND date=:date AND room_category_id=:roomCategoryId", nativeQuery = true)
    public int updatePrice(Long hotelId, Date date, int roomCategoryId, Map<String, Double> occupancyToPrice);
}
