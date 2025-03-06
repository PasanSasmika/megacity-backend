package com.cabservice.MegaCity.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    
    @Id
    private String bookingId;
    private String pickupLocation;
    private String dropLocation;
    private LocalDate date; 
    private LocalTime time; 
    private String customerName;
    private String customerID;
    private String email;
    private String phone;
    private String distance;
    private String total;
    private String driverID;
    private String bookingStatus;

}
