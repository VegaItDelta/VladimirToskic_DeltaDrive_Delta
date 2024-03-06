package com.example.DeltaDrive_Delta.services;

import com.example.DeltaDrive_Delta.entities.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> findClosestVehicle(double latitude, double longitude, int limit);
}
