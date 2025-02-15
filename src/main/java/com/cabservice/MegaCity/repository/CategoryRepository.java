package com.cabservice.MegaCity.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cabservice.MegaCity.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

    
} 