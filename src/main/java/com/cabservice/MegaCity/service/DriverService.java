package com.cabservice.MegaCity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.MegaCity.model.Driver;
import com.cabservice.MegaCity.repository.DriverRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    // Create Driver
    public String createDriver(Driver driver) {
        // Check if the email already exists
        if (driverRepository.findByDriverEmail(driver.getDriverEmail()).isPresent()) {
            return "Driver with this email already exists.";
        }
        driverRepository.save(driver);
        return "Driver account created successfully.";
    }
    

    // Get Driver By ID
    public Driver getDriverByID(String driverID) {
        return driverRepository.findById(driverID).orElseThrow();
    }

    // Get All Drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Delete Driver
    public String deleteDriverByID(String driverID) {
        driverRepository.deleteById(driverID);
        return driverID + " Driver Deleted Successfully";
    }

    // Get Driver By Category ID
    public List<Driver> getDriversByCategoryId(String catID) {
        List<Driver> drivers = driverRepository.findByCatID(catID);
        System.out.println("Searching for drivers with catID: " + catID);
        System.out.println("Found drivers: " + drivers);
        return drivers;
    }

    // Update Driver
    public Driver updateDriver(String driverID, Driver updatedDriver) {
        Driver existingDriver = driverRepository.findById(driverID)
                .orElseThrow();

        // Update all fields
        existingDriver.setDriverName(updatedDriver.getDriverName());
        existingDriver.setDriverAddress(updatedDriver.getDriverAddress());
        existingDriver.setDriverPhone(updatedDriver.getDriverPhone());
        existingDriver.setDriverEmail(updatedDriver.getDriverEmail());
        existingDriver.setUserName(updatedDriver.getUserName());
        existingDriver.setPassword(updatedDriver.getPassword());
        existingDriver.setDriverStatues(updatedDriver.getDriverStatues());

        return driverRepository.save(existingDriver);
    }
}