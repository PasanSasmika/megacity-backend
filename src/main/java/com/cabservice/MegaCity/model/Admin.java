package com.cabservice.MegaCity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "admins")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    
    @Id
    private String adminID;
    private String userName;
    private String password;

   
}
