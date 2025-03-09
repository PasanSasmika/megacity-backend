package com.cabservice.MegaCity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cabservice.MegaCity.model.Driver;
import com.cabservice.MegaCity.service.CloudinaryService;
import com.cabservice.MegaCity.service.DriverService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/createdriver")
    public ResponseEntity<String> createDriver(
        @RequestParam("imageUrl") MultipartFile imageUrl,
        @RequestParam("licenceImg") MultipartFile licenceImg,
        @RequestParam("vehicalName") String vehicalName, 
        @RequestParam("driverName") String driverName,
        @RequestParam("driverStatues") String driverStatues, 
        @RequestParam("driverEmail") String driverEmail,
        @RequestParam(value = "userName", required = false) String userName, 
        @RequestParam(value = "password", required = false) String password,
        @RequestParam("driverAddress") String driverAddress,
        @RequestParam("driverPhone") String driverPhone,
        @RequestParam("vehicalType") String vehicalType,
        @RequestParam("catType") String catType,
        @RequestParam("noOfSeats") String noOfSeats,
        @RequestParam("lagguageType") String lagguageType,
        @RequestParam("pricePerKm") String pricePerKm
    ) throws IOException {

        // Upload driver photo to Cloudinary
        String driverPhotoUrl = cloudinaryService.uploadImage(imageUrl); 
        String licenceImgUrl = cloudinaryService.uploadImage(licenceImg);

        // Create and populate the Driver object
        Driver driver = new Driver();
        driver.setDriverName(driverName);
        driver.setDriverEmail(driverEmail);
        driver.setImageUrl(driverPhotoUrl);
        driver.setLicenceImg(licenceImgUrl);
        driver.setDriverAddress(driverAddress);
        driver.setDriverPhone(driverPhone);
        driver.setDriverStatues("Pending"); // Default status is Pending
        driver.setCatType(catType);
        driver.setVehicalType(vehicalType);
        driver.setVehicalName(vehicalName);
        driver.setNoOfSeats(noOfSeats);
        driver.setPricePerKm(pricePerKm);
        driver.setLagguageType(lagguageType);

        if (userName != null) {
            driver.setUserName(userName);
        }
        if (password != null) {
            driver.setPassword(passwordEncoder.encode(password)); // Encode password if provided
        }

        // Save driver
        String result = driverService.createDriver(driver);
        if (result.equals("Driver with this email already exists.")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    
@PostMapping("/auth/setcredentials/{driverID}")
public ResponseEntity<String> setDriverCredentials(
        @PathVariable String driverID,
        @RequestParam String userName,
        @RequestParam String password) {

    // Retrieve the existing driver
    Driver existingDriver = driverService.getDriverByID(driverID);
    
    // Update username and encode password
    existingDriver.setUserName(userName);
    existingDriver.setPassword(passwordEncoder.encode(password));
    
    // Save the updated driver using the existing updateDriver method
    driverService.updateDriver(driverID, existingDriver);
    
    return ResponseEntity.ok("Driver credentials updated successfully.");
}

    // Get Driver by ID
    @GetMapping("/{driverID}")
    public Driver getDriverByID(@PathVariable String driverID) {
        return driverService.getDriverByID(driverID);
    }

    // Get All Drivers
    @GetMapping("/auth/alldrivers")
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // Delete Driver by ID
    @DeleteMapping("/auth/deletedriver/{driverID}")
    public String deleteDriver(@PathVariable String driverID) {
        return driverService.deleteDriverByID(driverID);
    }

    // Get Drivers by Category ID
    @GetMapping("/category/{catID}")
    public List<Driver> getDriversByCategory(@PathVariable String catID) {
        return driverService.getDriversByCategoryId(catID);
    }

    // Update Driver
    @PutMapping("/update/{driverID}")
    public Driver updateDriver(@PathVariable String driverID, @RequestBody Driver updatedDriver) {
        return driverService.updateDriver(driverID, updatedDriver);
    }
    
}