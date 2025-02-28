package com.cabservice.MegaCity.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabservice.MegaCity.model.Admin;
import com.cabservice.MegaCity.repository.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        System.out.println("Creating admin: " + admin);
        return adminRepository.save(admin);
    }
    public Admin getAdminById(String adminID) {
        return adminRepository.findById(adminID).get();
    }

    public List<Admin> getAllAdmis() {
        return adminRepository.findAll();
    }
}
