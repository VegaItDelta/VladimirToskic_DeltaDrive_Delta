package com.example.DeltaDrive_Delta.services;

import org.springframework.security.core.userdetails.UserDetails;


public interface JWTService {

     String genereteToken( UserDetails userDetails);
     String extractUsername(String token);
     boolean isTokenValid(String token, UserDetails userDetails);
}
