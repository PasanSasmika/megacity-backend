package com.cabservice.MegaCity.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String role;
    private String userId;


    public LoginResponse(String token, String role, String userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;

    }
}
