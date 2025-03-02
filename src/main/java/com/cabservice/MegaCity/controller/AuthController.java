package com.cabservice.MegaCity.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.MegaCity.Dto.AllLoginDto;
import com.cabservice.MegaCity.Dto.LoginResponse;
import com.cabservice.MegaCity.security.Jwt.JwtUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
      @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/auth/login") 
    public LoginResponse login(@RequestBody AllLoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwtToken = jwtUtils.generateJwtToken(authentication);

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String role = userDetails.getAuthorities().stream()
            .findFirst()
            .map(GrantedAuthority::getAuthority)
            .orElse("USER");

    String userId = jwtUtils.getUserIdFromJwtToken(jwtToken);

    return new LoginResponse(jwtToken, role, userId);
}
}
//