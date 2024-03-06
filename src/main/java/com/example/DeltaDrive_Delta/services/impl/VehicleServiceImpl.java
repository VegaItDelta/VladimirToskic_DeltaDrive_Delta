package com.example.DeltaDrive_Delta.services.impl;

import com.example.DeltaDrive_Delta.entities.Vehicle;
import com.example.DeltaDrive_Delta.repository.VehicleRepository;
import com.example.DeltaDrive_Delta.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private  VehicleRepository vehicleRepository;


    public Vehicle saveVehicle(Vehicle vehicle){
      return   vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }
    public Vehicle getVehicleById(Long id){
        return vehicleRepository.findById(id).orElseThrow();
    }

    public Vehicle updateVehicle(Vehicle vehicle){
        Vehicle existingVehicle = vehicleRepository.findById(vehicle.getId()).orElse(null);
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setDriverFirstName(vehicle.getDriverFirstName());
        existingVehicle.setDriverLastName(vehicle.getDriverLastName());
        existingVehicle.setStartPrice(vehicle.getStartPrice());
        existingVehicle.setPricePerKm(vehicle.getPricePerKm());
        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public List<Vehicle> findClosestVehicle(double longitude, double latitude, int limit) {
        List<Vehicle> allVehicles = vehicleRepository.findNearestVehicles(longitude, latitude);
        return allVehicles.subList(0, Math.min(allVehicles.size(), limit));
    }

}
