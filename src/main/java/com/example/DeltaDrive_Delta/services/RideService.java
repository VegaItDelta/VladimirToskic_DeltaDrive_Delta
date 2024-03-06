package com.example.DeltaDrive_Delta.services;

import com.example.DeltaDrive_Delta.dto.RideDto;
import com.example.DeltaDrive_Delta.entities.Ride;

public interface RideService {
    public Ride bookRide(RideDto rideDto);
    public Ride randomizeRideStatus(Long rideId);
    public Ride simulateRide(Long rideId);
}
