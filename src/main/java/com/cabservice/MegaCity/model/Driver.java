package com.cabservice.MegaCity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "driver")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
       @Id
    private String driverID;
    private String driverName;
    private String driverAddress;
    private String driverPhone;
    private String driverEmail;
    private String userName;
    private String password;
    private String driverStatues;
    private String vehicalName;
    private String vehicalType;
    private String vehicalModel;
    private String vehicalSeats;
    private String imageUrl;


    //Get Category IDfdgfncfgfgcv
    private String catID;
}
