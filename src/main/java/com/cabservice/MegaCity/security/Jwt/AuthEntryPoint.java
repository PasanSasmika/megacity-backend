package com.cabservice.MegaCity.security.Jwt;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
     @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws 
    IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
         
    }

}
