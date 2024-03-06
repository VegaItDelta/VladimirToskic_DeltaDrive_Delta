package com.example.DeltaDrive_Delta.services;

import com.example.DeltaDrive_Delta.entities.Ride;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    public UserDetailsService userDetailsService();

    List<Ride> getUserRides();
}
