package com.cabservice.MegaCity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.MegaCity.model.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver , String> {
    Optional<Driver> findByUserName(String userName);
    List<Driver> findByCatID(String catID);
    
} 
  

