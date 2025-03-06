package com.cabservice.MegaCity.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.cabservice.MegaCity.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String>  {
List<Booking> findByDriverID(String driverID);
    
} 