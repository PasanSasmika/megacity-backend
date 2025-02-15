package com.cabservice.MegaCity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    
     @Id
     
     private String customerId;
     private String name;
     private String email;
     private String phone;
     private String address;
     private String userName;
     private String password;
     private String customerProfile; 
}
