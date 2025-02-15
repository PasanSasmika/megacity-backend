package com.cabservice.MegaCity.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.MegaCity.model.Customer;

@Repository
public interface CustomerRepository extends  MongoRepository<Customer, String>   {

        Optional<Customer> findByUserName(String userName);

}