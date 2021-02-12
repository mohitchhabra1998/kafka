package com.techprimers.kafka.springbootkafkaconsumerexample.dao;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice2;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface RoomPrice2Repository extends CrudRepository<RoomPrice2,Long> {
    @Query(value = "SELECT * FROM roomprice2 WHERE id=:id", nativeQuery = true)
    public List<RoomPrice2> findById(long id);

    @Query(value = "SELECT * FROM roomprice2 WHERE hotel_id=:hotel_id AND date=:date AND room_category_id=:room_category_id", nativeQuery = true)
    public List<RoomPrice2> findByCombo(long hotel_id,Date date,int room_category_id);

    @Modifying
    @Query(value = "DELETE FROM roomprice2 WHERE hotel_id=:hotel_id AND date=:date AND room_category_id=:room_category_id", nativeQuery = true)
    public int deleterow(long hotel_id,Date date, int room_category_id);

    @Modifying
    @Query(value = "UPDATE roomprice2 SET otp=:otp WHERE hotel_id=:hotel_id AND date=:date AND room_category_id=:room_category_id", nativeQuery = true)
    public int updatePrice(Long hotel_id, Date date, int room_category_id, Map<String, Double> otp);
}
