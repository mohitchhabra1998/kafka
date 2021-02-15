package com.techprimers.kafka.springbootkafkaconsumerexample.dao;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.RoomPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface RoomPriceRepository extends CrudRepository<RoomPrice,Long> {

}
