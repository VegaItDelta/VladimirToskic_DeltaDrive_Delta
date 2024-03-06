package com.example.DeltaDrive_Delta.controller;

import com.example.DeltaDrive_Delta.entities.Vehicle;
import com.example.DeltaDrive_Delta.services.impl.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private  final VehicleServiceImpl vehicleService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN)")
    @PutMapping(value = "/updateVehicle")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.updateVehicle(vehicle);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN)")
    @GetMapping(value = "/allVehicles")
    public List<Vehicle> findAllVehicle(){
        return vehicleService.getVehicles();
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN)")
    @GetMapping(value = "/closest-vehicles")
    public List<Vehicle> findClosestVehicles(
            @RequestParam(value = "longitude") double longitude,
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "limit") int limit){
        return vehicleService.findClosestVehicle(longitude, latitude, limit);
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN)")
    @GetMapping("/vehicle/{id}")
    public Vehicle findVehicleById(@PathVariable Long id){
        return vehicleService.getVehicleById(id);
    }
}
