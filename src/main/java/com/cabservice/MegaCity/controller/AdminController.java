package com.cabservice.MegaCity.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.MegaCity.model.Admin;
import com.cabservice.MegaCity.service.AdminService;

@RestController
 @CrossOrigin(origins = "*")
 @RequestMapping("/api/admin")

public class AdminController {
    
   @Autowired
    private AdminService adminService;
    @Autowired
        private PasswordEncoder passwordEncoder;


    @PostMapping("/createadmin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminService.createAdmin(admin);
    }

    @GetMapping("/auth/alladmins/{adminID}")
    public Admin getAdminId(@PathVariable String adminID) {
        return adminService.getAdminById(adminID);
    }
}
