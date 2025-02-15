package com.cabservice.MegaCity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cabservice.MegaCity.model.Driver;
import com.cabservice.MegaCity.service.DriverService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController

public class DriverController {

    @Autowired
    private DriverService driverService;

    // Create Driver
    @PostMapping("/auth/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
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
    @DeleteMapping("/{driverID}")
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
