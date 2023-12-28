package com.example.supportlearningjp.service;


import com.example.supportlearningjp.model.User;
import io.jsonwebtoken.Claims;


import java.security.Key;

public interface JWTService {
     String generateToken(User user);
     String generateRefreshToken(User user);
     String getEmailFromJwt(String token);

      String getEmailFromRefreshToken(String token);
     String[] getRolesFromJwt(String token);

     boolean validateToken(String token);
}
