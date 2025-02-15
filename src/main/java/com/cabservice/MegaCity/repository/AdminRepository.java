package com.cabservice.MegaCity.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cabservice.MegaCity.model.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    Optional<Admin> findByUserName(String userName);

} 