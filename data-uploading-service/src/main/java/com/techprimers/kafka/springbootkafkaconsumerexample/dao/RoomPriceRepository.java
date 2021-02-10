package com.techprimers.kafka.springbootkafkaconsumerexample.dao;


import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface RoomPriceRepository extends CrudRepository<RoomPrice,Long>{

    @Query(value = "SELECT price FROM roomprice WHERE hotel_id=:hotel_id AND date=:date AND room_category_id=:room_category_id AND occupancy=:occupancy", nativeQuery = true)
    public List<Double> getPrice(long hotel_id, Date date, int room_category_id, int occupancy);

    @Modifying
    @Query(value = "UPDATE roomprice SET price=:price WHERE hotel_id=:hotel_id AND date=:date AND room_category_id=:room_category_id AND occupancy=:occupancy", nativeQuery = true)
    public void updatePrice(long hotel_id,Date date, int room_category_id, int occupancy,double price);

    @Modifying
    @Query(value = "DELETE FROM roomprice WHERE hotel_id=:hotel_id AND date=:date AND room_category_id=:room_category_id AND occupancy=:occupancy", nativeQuery = true)
    public void deleterow(long hotel_id,Date date, int room_category_id, int occupancy);


}
